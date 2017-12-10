package cyan.nazgul.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.cache.CacheBuilderSpec;
import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.auth.SuperAdmin;
import cyan.nazgul.dropwizard.config.CrossdomainConfig;
import cyan.nazgul.dropwizard.config.ProjectConfig;
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

    /*========= Config ==========*/
    @JsonProperty("project")
    private ProjectConfig projectConfig;

    public ProjectConfig getProjectConfig() {
        return projectConfig;
    }

    public void setProjectConfig(ProjectConfig projectConfig) {
        this.projectConfig = projectConfig;
    }

    /*========= CrossDomain ==========*/
    @JsonProperty("crossdomain")
    private CrossdomainConfig crossdomainConfig;

    public CrossdomainConfig getCrossdomainConfig() {
        return crossdomainConfig;
    }

    public void setCrossdomainConfig(CrossdomainConfig crossdomainConfig) {
        this.crossdomainConfig = crossdomainConfig;
    }

    /*========= Swagger ==========*/
    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;

    /*========== SuperAdmin ==========*/
    public SuperAdmin getSuperadmin() {
        return superadmin;
    }

    public void setSuperadmin(SuperAdmin superadmin) {
        this.superadmin = superadmin;
    }

    @JsonProperty("superadmin")
    private SuperAdmin superadmin;

    /*========== Cache Policy ==========*/
    public CacheBuilderSpec getAuthenticationCachePolicy() {
        return authenticationCachePolicy;
    }

    public void setAuthenticationCachePolicy(CacheBuilderSpec authenticationCachePolicy) {
        this.authenticationCachePolicy = authenticationCachePolicy;
    }

    @JsonProperty("authenticationCachePolicy")
    CacheBuilderSpec authenticationCachePolicy;
}
