package cyan.svc.nazgulexample.resources;

import com.codahale.metrics.annotation.Counted;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import cyan.nazgul.dropwizard.resources.GenericEntityResource;
import cyan.svc.nazgulexample.Configuration;
import cyan.svc.output.EntityOutput;
import io.dropwizard.setup.Environment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Optional;

/**
 * Entity资源实现了实体对象最基本的CURDL功能，因此在业务的开发中不再实现基本的CURDL接口。
 * 虽然使用动态技术，API性能下降，但能大大提升原型的开发效率。
 * 在线上运行中可根据具体情况再对性能瓶颈部分使用Mybatis或JDBI技术进行查询优化。或者缓存优化。
 */
@Api(value = "/entity 实体REST管理", description = "提供了泛类型JPA实体的CURDL基础API")
@Path(value = "/entity")
@Produces(MediaType.APPLICATION_JSON)
@Counted
@Timed
@Metered(name = "EntityResource")
public class EntityResource extends GenericEntityResource {

    /*========== Static Properties =========*/

    /*========== Properties =========*/

    /*========== Constructor =========*/
    public EntityResource(Configuration config, Environment env) {
        super( config, env);
    }


    /*========= Class Definition API ==========*/
    @ApiOperation(value = "获取支持的实体列表",
            notes = " "
    )
    @GET
    @Path("/clz")
    public EntityOutput getClzList() {
        return super.getClzList();
    }

    @ApiOperation(value = "获取指定实体的字段定义",
            notes = "该API用于输出指定实体的JsonSchema定义"
    )
    @GET
    @Path("/clz/{clz}/jsonschema")
    public Response getClzJsonSchema(
            @ApiParam(value = "JPA类的名称，entities文件夹以下相对路径,子路径用'.'分隔,例如admin.SuperAdmin", required = true, example = "Person") @PathParam("clz") String clz
    ) throws IllegalAccessException, InstantiationException, IOException {
        return super.getClzJsonSchema(clz);
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
       return super.getList(clz, search, pageStart, pageSize);
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
        return super.get(clz, id);
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
        return super.create(clz, obj);
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
        return super.update(clz, id, obj);
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
        return super.delete(clz,id);
    }
}
