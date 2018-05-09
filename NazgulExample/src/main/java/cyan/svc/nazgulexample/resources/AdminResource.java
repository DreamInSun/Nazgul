package cyan.svc.nazgulexample.resources;

import cyan.nazgul.dropwizard.auth.superadmin.SuperAdmin;
import cyan.nazgul.dropwizard.config.BaseSvcConfig;
import cyan.nazgul.dropwizard.resources.DbResource;
import cyan.svc.output.EntityOutput;
import cyan.svc.err.BaseErrCode;
import cyan.svc.err.IErrInfoMapper;
import cyan.svc.nazgulexample.Configuration;
import io.dropwizard.auth.Auth;
import io.dropwizard.setup.Environment;
import io.swagger.annotations.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 超级管理员界面，用于一些基础信息的调试
 */
@SwaggerDefinition(
        securityDefinition = @SecurityDefinition(
                basicAuthDefinions = {
                        @BasicAuthDefinition(key = "basicAuth")}
            )
)
@Api(value = "/admin 超级管理员管理模块", description = "Person Management")
@Path(value = "/admin")
@Produces(MediaType.APPLICATION_JSON)
public class AdminResource extends DbResource<Configuration> {

    /*========== Properties =========*/
    private BaseSvcConfig m_svcConfig;

    /*========== Constructor =========*/
    public AdminResource(Configuration config, Environment env) {
        super(config, env);
        m_svcConfig = this.getConfig().getSvcConfig();
    }

    @Override
    public int initialize(Configuration configuration, Environment env) {
        super.initialize(configuration, env);
        return 0;
    }

    /*========== 获取依赖配置 =========*/
    @ApiOperation(value = "获取配置信息", response = EntityOutput.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "No Name Provided")
    })
    @GET
    @Path("/config/svcConfig")
    public EntityOutput getSvcConfig() {
        return EntityOutput.getInstance(BaseErrCode.SUCCESS, m_svcConfig);
    }


    /*========== 查询错误代码 ==========*/
    @ApiOperation(value = "根据错误代码获取错误信息", response = EntityOutput.class, authorizations = @Authorization(value = "SuperAdmin"))
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "找到错误信息")
    })
    @GET
    @Path("/output/errCode/{errCode}/errInfo")
    public EntityOutput findErrorInfo(
            @ApiParam(hidden = true) @Auth SuperAdmin superAdmin,
            @ApiParam(value = "错误代码", required = true) @PathParam("errCode") Integer errCode
    ) {
        String errInfo = null;
        IErrInfoMapper errInfoMap = EntityOutput.getErrInfoMapping();
        if (errInfoMap != null) {
            errInfo = errInfoMap.getInfo(errCode);
        }
        return EntityOutput.getInstance(BaseErrCode.SUCCESS, errInfo);
    }
}



