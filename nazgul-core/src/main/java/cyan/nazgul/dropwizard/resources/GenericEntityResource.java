package cyan.nazgul.dropwizard.resources;

import com.codahale.metrics.annotation.Counted;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import com.github.tennaito.rsql.jpa.JpaCriteriaQueryVisitor;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cyan.nazgul.dropwizard.DbConfiguration;
import cyan.nazgul.dropwizard.config.ProjectConfig;
import cyan.svc.EntityOutput;
import cyan.svc.entity.BaseEntity;
import cyan.svc.err.BaseErrCode;
import cyan.util.JsonUtil;
import cyan.util.clazz.ClassUtil;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import io.dropwizard.setup.Environment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Entity资源实现了实体对象最基本的CURDL功能，因此在业务的开发中不再实现基本的CURDL接口。
 * 虽然使用动态技术，API性能下降，但能大大提升原型的开发效率。
 * 在线上运行中可根据具体情况再对性能瓶颈部分使用Mybatis或JDBI技术进行查询优化。或者缓存优化。
 */
@Api(value = "实体基础API", description = "Entity CURDL")
@Path(value = "/rest")
@Produces(MediaType.APPLICATION_JSON)
@Counted
@Timed
@Metered(name = "EntityResource")
@SuppressWarnings("uncheked")
public class GenericEntityResource extends DbResource<DbConfiguration> {

    /*========== Static Properties =========*/
    public static final int DFLT_PAGE_IDX = 1;
    public static final int DFLT_PAGE_SIZE = 50;

    /*========== Properties =========*/
    private ProjectConfig m_projectConfig;
    private String m_entityPackage;
    private final Map<String, Class<?>> m_entityClzMap = Maps.newHashMap();

    /*========== Constructor =========*/
    public GenericEntityResource(DbConfiguration config, Environment env) {
        super(config, env);
        m_projectConfig = config.getProjectConfig();
        m_entityPackage = m_projectConfig.getRootPackage() + ".entities";
        /*===== Scan Entities =====*/
        List<Class<?>> clzList = ClassUtil.getClassList(m_entityPackage, true, Entity.class);
        for (Class<?> entityClazz : clzList) {
            /*========== Create Resource Instance ==========*/
            try {
                /* Patch for debug file */
                if (entityClazz.getName().endsWith("$1")) continue;
                /* */
                String clzName = entityClazz.getName().substring(m_entityPackage.length() + 1);
                m_entityClzMap.put(clzName, entityClazz);
            } catch (Exception e) {
                this.getLogger().error(e.getMessage());
            }
        }
    }

    @Override
    public int initialize(DbConfiguration config, Environment env) {
        super.initialize(config, env);
        return 0;
    }

    /*========= Class Definition API ==========*/
    @ApiOperation(value = "获取支持的实体列表",
            notes = " "
    )
    @GET
    @Path("/clz")
    public EntityOutput getClzList() {
        List<String> retList = Lists.newArrayList(m_entityClzMap.keySet());
        Collections.sort(retList);
        return EntityOutput.getInstance(BaseErrCode.SUCCESS, retList);
    }

    @ApiOperation(value = "获取指定实体的字段定义",
            notes = "该API用于输出指定实体的JsonSchema定义"
    )
    @GET
    @Path("/clz/{clz}/jsonschema")
    public Response getClzJsonSchema(
            @ApiParam(value = "JPA类的名称，entities文件夹以下相对路径,子路径用'.'分隔,例如admin.SuperAdmin", required = true, example = "Person") @PathParam("clz") String clz
    ) throws IllegalAccessException, InstantiationException, IOException {
        /*===== Class =====*/
        Class<?> entityClz = m_entityClzMap.get(clz);
        if (null == entityClz) {
            return Response.noContent().build();
        }

        /*===== Format Entity Definition =====*/
        String schemaStr = JsonUtil.getJsonSchema(entityClz);
        return Response.ok(schemaStr).build();
    }

    /*========= GET / Retrieve ==========*/
    @ApiOperation(value = "按照查询语句获取实体列表",
            notes = "- name==\"Kill Bill\";year=gt=2003\n" +
                    "- name==\"Kill Bill\" and year>2003\n" +
                    "- genres=in=(sci-fi,action);(director=='Christopher Nolan',actor==*Bale);year=ge=2000\n" +
                    "- genres=in=(sci-fi,action) and (director=='Christopher Nolan' or actor==*Bale) and year>=2000\n" +
                    "- director.lastName==Nolan;year=ge=2000;year=lt=2010\n" +
                    "- director.lastName==Nolan and year>=2000 and year<2010\n" +
                    "- genres=in=(sci-fi,action);genres=out=(romance,animated,horror),director==Que*Tarantino\n" +
                    "- genres=in=(sci-fi,action) and genres=out=(romance,animated,horror) or director==Que*Tarantino"
    )
    @GET
    @Path("/{clz}")
    @Produces(MediaType.APPLICATION_JSON)
    public EntityOutput getList(
            @ApiParam(value = "JPA类的名称，entities文件夹以下相对路径,子路径用'.'分隔,例如admin.SuperAdmin", required = true, example = "Person") @PathParam("clz") String clz,
            @ApiParam(value = "RSQL查询语句,参看ISO-14977标准", required = true, example = "age>=8") @QueryParam("search") String search,
            @ApiParam(value = "分页", required = false, example = "1") @QueryParam("page") @DefaultValue("1") Optional<Integer> pageStart,
            @ApiParam(value = "页面大小", required = false, example = "10") @QueryParam("size") @DefaultValue("10") Optional<Integer> pageSize
    ) {
        /*===== Class =====*/
        Class<?> entityClz = m_entityClzMap.get(clz);
        if (null == entityClz) {
            return EntityOutput.getInstance(BaseErrCode.ENTITY_CLASS_NOT_FOUND);
        }
        /*===== Create Generic JpaCriteriaQueryVisitor =====*/
        RSQLVisitor<CriteriaQuery, EntityManager> visitor = null;
        try {
            visitor = getEntityVisitor(entityClz);
        } catch (Exception e) {
            this.getLogger().error(e.getMessage());
            return EntityOutput.getInstance(BaseErrCode.ENTITY_CLASS_JPA_ERROR);
        }
        /*===== Build Dynamic SQL =====*/
        RSQLParser rsqlParser = new RSQLParser();
        //伪删除筛选
        search = search + " and itemStat == 1";
        CriteriaQuery query = rsqlParser.parse(search).accept(visitor, this.getEntityManager());
        if (query == null) {
            return EntityOutput.getInstance(BaseErrCode.ENTITY_QUERY_STRING_ERROR);
        }
        /*===== Order =====*/
        //query.orderBy(orders);
        /*===== Page =====*/
        Integer iPageStart = pageStart.orElse(DFLT_PAGE_IDX);
        Integer iPageSize = pageSize.orElse(DFLT_PAGE_SIZE);
        Integer pageIdx = (iPageStart - 1) * iPageSize;
        /*===== Execute Query =====*/
        List<Object> resultList = this.getEntityManager().createQuery(query).setFirstResult(pageIdx).setMaxResults(iPageSize).getResultList();
        return EntityOutput.getInstance(BaseErrCode.SUCCESS, resultList);
    }

    @ApiOperation(value = "根据ID获取实体",
            notes = ""
    )
    @GET
    @Path("/{clz}/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EntityOutput get(
            @ApiParam(value = "JPA类的名称，entities文件夹以下相对路径,子路径用'.'分隔,例如admin.SuperAdmin", required = true, example = "Person") @PathParam("clz") String clz,
            @ApiParam(value = "实体逻辑主键ID，继承自BaseEntity, 注意数据类型为Long，持久化类型为BIGINT", required = true, example = "1") @PathParam("id") Long id
    ) {
        Object retObj = null;
        /*===== Class =====*/
        Class<?> entityClz = m_entityClzMap.get(clz);
        if (null == entityClz) {
            return EntityOutput.getInstance(BaseErrCode.ENTITY_CLASS_NOT_FOUND);
        }
        /*===== Query =====*/
        EntityManager entityMngr = this.getEntityManager();
        retObj = entityMngr.find(entityClz, id);
        /*===== Return =====*/
        return EntityOutput.getInstance(BaseErrCode.SUCCESS, retObj);
    }

    @ApiOperation(value = "创建实体并返回",
            notes = "   {\"pid\": 5,\"name\": \"Tester\",\"firstName\": \"Robert\",\"lastName\": \"Willson\"}"
    )
    @POST
    @Path("/{clz}")
    @Consumes(MediaType.APPLICATION_JSON)
    public EntityOutput create(
            @ApiParam(value = "JPA类的名称，entities文件夹以下相对路径", required = true, example = "Person") @PathParam("clz") String clz,
            @ApiParam(value = "实体对象", required = true, example = "{\"pid\": 2,\"name\": \"Tester\",\"firstName\": \"Robert\",\"lastName\": \"Willson\"}") Object obj
    ) {
        /*===== Class =====*/
        Class<?> entityClz = m_entityClzMap.get(clz);
        if (null == entityClz) {
            return EntityOutput.getInstance(BaseErrCode.ENTITY_CLASS_NOT_FOUND);
        }
        /*===== Reflection Object =====*/
        Object entity = null;
        try {
            entity = entityClz.newInstance();
            BeanUtils.copyProperties(entity, obj);
        } catch (Exception e) {
            this.getLogger().error(e.getMessage());
        }

        if (entity == null) {
            return EntityOutput.getInstance(BaseErrCode.ENTITY_CLASS_REFLECTION_ERROR);
        }
        /*===== Persistence =====*/
        EntityManager entityMngr = this.getEntityManager();
        EntityTransaction entityTransaction = entityMngr.getTransaction();
        entityTransaction.begin();
        try {
            entityMngr.persist(entity);
        } catch (IllegalArgumentException e) {
            return EntityOutput.getInstance(BaseErrCode.ENTITY_DIFINITION_ERROR, e.getMessage().toString(), null);
        } catch (PersistenceException e) {
            return EntityOutput.getInstance(BaseErrCode.ENTITY_QUERY_PERSISTENCE_ERROR, e.getMessage().toString(), null);
        } catch (Exception e) {
            e.printStackTrace();
            //return EntityOutput.getInstance(BaseErrCode.ENTITY_QUERY_PERSISTENCE_ERROR, e.getMessage().toString(), null);
        }
        entityTransaction.commit();
        entityMngr.close();
        /*===== Return =====*/
        return EntityOutput.getInstance(BaseErrCode.SUCCESS, entity);
    }

    @ApiOperation(value = "更新实体可更新的属性",
            notes = "{ \"age\": \"12\"}"
    )
    @PUT
    @Path("/{clz}/id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public EntityOutput update(
            @ApiParam(value = "JPA类的名称，entities文件夹以下相对路径", required = true, example = "Person") @PathParam("clz") String clz,
            @ApiParam(value = "实体逻辑主键ID，继承自BaseEntity, 注意数据类型为Long，持久化类型为BIGINT", required = true, example = "1") @PathParam("id") Long id,
            @ApiParam(value = "实体对象", required = true, example = "{'id'}") Object obj) {
        /*===== Class =====*/
        Class<?> entityClz = m_entityClzMap.get(clz);
        if (null == entityClz) {
            return EntityOutput.getInstance(BaseErrCode.ENTITY_CLASS_NOT_FOUND);
        }
        /*===== Reflection Object =====*/
        EntityManager entityMngr = this.getEntityManager();
        Object entity = null;
        try {
            entity = entityMngr.find(entityClz, id);
            BeanUtils.copyProperties(entity, obj);
            ((BaseEntity) entity).setId(id);
        } catch (Exception e) {
            this.getLogger().error(e.getMessage());
        }
        if (entity == null) {
            return EntityOutput.getInstance(BaseErrCode.ENTITY_CLASS_REFLECTION_ERROR);
        }
        /*===== Persistence =====*/
        EntityTransaction entityTransaction = entityMngr.getTransaction();
        entityTransaction.begin();
        try {
            entityMngr.merge(entity);
            entityMngr.flush();
        } catch (IllegalArgumentException e) {
            return EntityOutput.getInstance(BaseErrCode.ENTITY_DIFINITION_ERROR, e.getMessage().toString(), null);
        } catch (PersistenceException e) {
            return EntityOutput.getInstance(BaseErrCode.ENTITY_QUERY_PERSISTENCE_ERROR, e.getMessage().toString(), null);
        } catch (Exception e) {
            e.printStackTrace();
            //return EntityOutput.getInstance(BaseErrCode.ENTITY_QUERY_PERSISTENCE_ERROR, e.getMessage().toString(), null);
        }
        entityTransaction.commit();
        entityMngr.close();
        /*===== Class =====*/
        return EntityOutput.getInstance(BaseErrCode.SUCCESS, entity);
    }

    @ApiOperation(value = "删除实体记录",
            notes = "默认为伪删除，即设置itemStat=0"
    )
    @DELETE
    @Path("/{clz}/id/{id}")
    public EntityOutput delete(
            @ApiParam(value = "JPA类的名称，entities文件夹以下相对路径", required = true, example = "Person") @PathParam("clz") String clz,
            @ApiParam(value = "实体逻辑主键ID，继承自BaseEntity, 注意数据类型为Long，持久化类型为BIGINT", required = true, example = "1") @PathParam("id") Long id
    ) {
  /*===== Class =====*/
        Class<?> entityClz = m_entityClzMap.get(clz);
        if (null == entityClz) {
            return EntityOutput.getInstance(BaseErrCode.ENTITY_CLASS_NOT_FOUND);
        }
        /*===== Reflection Object =====*/
        EntityManager entityMngr = this.getEntityManager();
        Object entity = null;
        try {
            entity = entityMngr.find(entityClz, id);
        } catch (Exception e) {
            this.getLogger().error(e.getMessage());
        }
        if (entity == null) {
            return EntityOutput.getInstance(BaseErrCode.ENTITY_CLASS_REFLECTION_ERROR);
        }
        /*===== Persistence =====*/
        EntityTransaction entityTransaction = entityMngr.getTransaction();
        entityTransaction.begin();
        try {
            ((BaseEntity) entity).delete();
            entityMngr.merge(entity);
            entityMngr.flush();
        } catch (IllegalArgumentException e) {
            return EntityOutput.getInstance(BaseErrCode.ENTITY_DIFINITION_ERROR, e.getMessage().toString(), null);
        } catch (PersistenceException e) {
            return EntityOutput.getInstance(BaseErrCode.ENTITY_QUERY_PERSISTENCE_ERROR, e.getMessage().toString(), null);
        } catch (Exception e) {
            e.printStackTrace();
            //return EntityOutput.getInstance(BaseErrCode.ENTITY_QUERY_PERSISTENCE_ERROR, e.getMessage().toString(), null);
        }
        entityTransaction.commit();
        entityMngr.close();
        return EntityOutput.getInstance(BaseErrCode.SUCCESS);
    }

    /*========  ===========*/
    RSQLVisitor<CriteriaQuery, EntityManager> getEntityVisitor(Class<?> entityClz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        RSQLVisitor<CriteriaQuery, EntityManager> visitor = null;
        /* Get Constructor */
        Constructor constructor = (JpaCriteriaQueryVisitor.class.getConstructors())[0];
        /* Create Instance */
        Object[] entityClzPara = {entityClz.newInstance()};
        Object[] parameters = {entityClzPara};
        visitor = (RSQLVisitor<CriteriaQuery, EntityManager>) constructor.newInstance(parameters);
        return visitor;
    }
}
