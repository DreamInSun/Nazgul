package cyan.svc.nazgulexample.resources;

import com.codahale.metrics.annotation.Counted;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.github.tennaito.rsql.jpa.JpaCriteriaQueryVisitor;
import cyan.nazgul.dropwizard.resources.DbResource;
import cyan.svc.EntityOutput;
import cyan.svc.nazgulexample.Configuration;
import cyan.svc.nazgulexample.config.SuperAdminConfig;
import cyan.svc.nazgulexample.contract.ErrCode;
import cyan.svc.nazgulexample.entities.Person;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import io.dropwizard.setup.Environment;
import io.swagger.annotations.*;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Entity资源实现了实体对象最基本的CURDL功能，因此在业务的开发中不再实现基本的CURDL接口。
 * 虽然使用动态技术，API性能下降，但能大大提升原型的开发效率。
 * 在线上运行中可根据具体情况再对性能瓶颈部分使用Mybatis或JDBI技术进行查询优化。或者缓存优化。
 */
@Api(value = "/entity 演示资源", description = "Person Management")
@Path(value = "/entity")
@Produces(MediaType.TEXT_HTML)
@Counted
@Timed
@Metered(name = "EntityResource")
public class EntityResource extends DbResource<Configuration> {

    /*========== Properties =========*/
    SuperAdminConfig m_superAdminConfig;

    /*========== Constructor =========*/
    public EntityResource(Configuration config, Environment env) {
        super(config, env);
        m_superAdminConfig = this.getConfig().getSuperAdminConfig();
    }

    @Override
    public int initialize(Configuration configuration, Environment env) {
        super.initialize(configuration, env);
        this.getLogger().info("PersonResource initializing.");
        return 0;
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
    public EntityOutput get(
            @ApiParam(value = "JPA类的名称，entities文件夹以下相对路径", required = true, example = "id==1") @QueryParam("clz") String clz,
            @ApiParam(value = "RSQL查询语句,参看ISO-14977标准", required = true, example = "id==1") @QueryParam("search") String search,
            @ApiParam(value = "分页", required = true, example = "id==1") @QueryParam("search") Integer page,
            @ApiParam(value = "页面大小", required = true, example = "id==1") @QueryParam("search") Integer size,
            @Context ServletContext application) {


        EntityManager entityMngr = this.getEntityManager();
        // Create the JPA Visitor
        RSQLVisitor<CriteriaQuery<Person>, EntityManager> visitor = new JpaCriteriaQueryVisitor<>();
        // Parse a RSQL into a Node
        Node rootNode = new RSQLParser().parse(search);
        // Visit the node to retrieve CriteriaQuery
        CriteriaQuery<Person> query = rootNode.accept(visitor, entityMngr);
        // Do all sort of operations you want with the criteria query
        //List<Order> orders = createOrder(from, cb, q.getSort());
        //query.orderBy(orders);
        // Execute and get results
        List<Person> persons = entityMngr.createQuery(query).getResultList();
        return EntityOutput.getInstance(ErrCode.SUCCESS, persons);
    }

    @POST
    @Path("/{clz}")
    @Consumes(MediaType.APPLICATION_JSON)
    public EntityOutput create(
            @ApiParam(value = "JPA类的名称，entities文件夹以下相对路径", required = true, example = "id==1") @QueryParam("clz") String clz,
            @ApiParam(value = "实体对象", required = true, example = "id==1") Object obj
    ) {
        return EntityOutput.getInstance(ErrCode.SUCCESS);
    }

    @PUT
    @Path("/{clz}")
    @Consumes(MediaType.APPLICATION_JSON)
    public EntityOutput update(
            @ApiParam(value = "JPA类的名称，entities文件夹以下相对路径", required = true, example = "id==1") @QueryParam("clz") String clz,
            @ApiParam(value = "实体对象", required = true, example = "id==1") Object obj) {

        return EntityOutput.getInstance(ErrCode.SUCCESS);
    }

    @DELETE
    @Path("/{clz}/id/{id}")
    public EntityOutput delete(
            @ApiParam(value = "JPA类的名称，entities文件夹以下相对路径", required = true, example = "id==1") @QueryParam("clz") String clz) {

        return EntityOutput.getInstance(ErrCode.SUCCESS);
    }

}
