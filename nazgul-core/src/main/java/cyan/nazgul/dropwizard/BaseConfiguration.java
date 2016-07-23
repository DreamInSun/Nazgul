package cyan.nazgul.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import cyan.nazgul.docker.svc.EnvConfig;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

/**
 * 基础应用配置，默认内置模块：
 * - Swagger
 * Created by DreamInSun on 2016/7/21.
 */
public class BaseConfiguration extends Configuration {

    /*========= Docker Env ==========*/
    public EnvConfig envConfig;

    /*========= Swagger ==========*/
    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;

}
