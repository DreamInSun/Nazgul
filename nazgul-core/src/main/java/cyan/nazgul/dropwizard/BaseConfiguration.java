package cyan.nazgul.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.cache.CacheBuilderSpec;
import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.auth.superadmin.SuperAdmin;
import cyan.nazgul.dropwizard.config.AuthConfig;
import cyan.nazgul.dropwizard.config.BaseSvcConfig;
import cyan.nazgul.dropwizard.config.CrossdomainConfig;
import cyan.nazgul.dropwizard.config.ProjectConfig;
import de.spinscale.dropwizard.jobs.JobConfiguration;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.secnod.dropwizard.shiro.ShiroConfiguration;

import java.util.Collections;
import java.util.Map;

/**
 * 基础应用配置，默认内置模块：
 * - Swagger
 * Created by DreamInSun on 2016/7/21.
 */
public class BaseConfiguration<TSvcConfig extends BaseSvcConfig> extends Configuration
        implements JobConfiguration{

    /*========= Docker Env ==========*/
    public EnvConfig envConfig;

    public EnvConfig getEnvConfig() {
        return envConfig;
    }

    public void setEnvConfig(EnvConfig envConfig) {
        this.envConfig = envConfig;
    }

    /*========= Config ==========*/
    @JsonProperty("project")
    protected ProjectConfig projectConfig;

    public ProjectConfig getProjectConfig() {
        return projectConfig;
    }

    public void setProjectConfig(ProjectConfig projectConfig) {
        this.projectConfig = projectConfig;
    }

    /*========= Component Map =========*/
    @JsonProperty("nazComponents")
    protected Map<String, Boolean> nazComponents;

    public Map<String, Boolean> getNazComponents() {
        return nazComponents;
    }

    public void setNazComponents(Map<String, Boolean> nazComponents) {
        this.nazComponents = nazComponents;
    }

    /*========= CrossDomain ==========*/
    @JsonProperty("crossdomain")
    protected CrossdomainConfig crossdomainConfig;

    public CrossdomainConfig getCrossdomainConfig() {
        return crossdomainConfig;
    }

    public void setCrossdomainConfig(CrossdomainConfig crossdomainConfig) {
        this.crossdomainConfig = crossdomainConfig;
    }

    /*========= Auth ==========*/
    @JsonProperty("auth")
    protected AuthConfig authConfig;

    public AuthConfig getAuthConfig() {
        return authConfig;
    }

    public void setAuthConfig(AuthConfig authConfig) {
        this.authConfig = authConfig;
    }

    /*========= JWT-Cookie ==========*/


    /*========= Shiro ==========*/
    @JsonProperty("shiro")
    protected ShiroConfiguration shiroConfig;

    public ShiroConfiguration getShiroConfig() {
        return shiroConfig;
    }

    public void setShiroConfig(ShiroConfiguration shiroConfig) {
        this.shiroConfig = shiroConfig;
    }

    /*========= Swagger ==========*/
    @JsonProperty("swagger")
    protected SwaggerBundleConfiguration swaggerBundleConfiguration;

    public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
        return swaggerBundleConfiguration;
    }

    public void setSwaggerBundleConfiguration(SwaggerBundleConfiguration swaggerBundleConfiguration) {
        this.swaggerBundleConfiguration = swaggerBundleConfiguration;
    }

    /*========== SuperAdmin ==========*/
    @JsonProperty("superadmin")
    protected SuperAdmin superadmin;

    public SuperAdmin getSuperadmin() {
        return superadmin;
    }

    public void setSuperadmin(SuperAdmin superadmin) {
        this.superadmin = superadmin;
    }

    /*========== SvcConfig ==========*/
    @JsonProperty("svc")
    protected TSvcConfig svcConfig;

    public TSvcConfig getSvcConfig() {
        return svcConfig;
    }

    public void setSvcConfig(TSvcConfig svcConfig) {
        this.svcConfig = svcConfig;
    }

    /*========== Cache Policy ==========*/
    public CacheBuilderSpec getAuthenticationCachePolicy() {
        return authenticationCachePolicy;
    }

    public void setAuthenticationCachePolicy(CacheBuilderSpec authenticationCachePolicy) {
        this.authenticationCachePolicy = authenticationCachePolicy;
    }

    @JsonProperty("authenticationCachePolicy")
    CacheBuilderSpec authenticationCachePolicy;

    /*========== Implements : JobConfiguration ==========*/
    public Map<String, String> getJobs() {
        return Collections.emptyMap();
    }
}
