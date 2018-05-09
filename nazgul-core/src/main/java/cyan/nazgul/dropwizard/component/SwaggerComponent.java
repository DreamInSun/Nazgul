package cyan.nazgul.dropwizard.component;

import com.fasterxml.jackson.annotation.JsonInclude;
import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import cyan.nazgul.dropwizard.container.GlobalInstance;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.swagger.jaxrs.config.BeanConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Swagger组件
 *
 * @see <a>https://github.com/smoketurner/dropwizard-swagger</a>
 * Created by DreamInSun on 2016/7/21.
 */
@SuppressWarnings("unchecked")
public class SwaggerComponent<TConfig extends BaseConfiguration> implements IComponent<TConfig> {
    private static final Logger g_Logger = LoggerFactory.getLogger(SwaggerComponent.class);

    /*========== Properties ==========*/
    private String g_rookPackage;

    /*========== Constructor ==========*/
    public SwaggerComponent(String rootPackage) {
        g_rookPackage = rootPackage;
    }

    /*========== Interface : IComponent ==========*/
    @Override
    public void init(Bootstrap bootstrap) {
    /*===== Add Bundle =====*/
        bootstrap.addBundle(new SwaggerBundle<TConfig>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(TConfig config) {
                String scanRootPackage = config.getProjectConfig().getRootPackage() + ".resources";
                g_Logger.info("Auto Fill Swagger Scan Root Package is :" + scanRootPackage);
                config.getSwaggerBundleConfiguration().setResourcePackage(scanRootPackage);
                return  config.getSwaggerBundleConfiguration();
            }
        });
    }

    @Override
    public void postInit(EnvConfig envConfig, Bootstrap<TConfig> bootstrap) {
//        /*===== Auto Fill Swagger Scan Package =====*/
//        BaseConfiguration config = GlobalInstance.getConfiguration();
//        String scanRootPackage = config.getProjectConfig().getRootPackage() + ".resources";
//        config.swaggerBundleConfiguration.setResourcePackage(scanRootPackage);
    }

    @Override
    public void run(TConfig baseConfiguration, Environment environment) {

        /* Register Listing */
//        environment.jersey().register(io.swagger.jaxrs.listing.ApiListingResource.class);
//        environment.jersey().register(io.swagger.jaxrs.listing.AcceptHeaderApiListingResource.class);
//        environment.jersey().register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        /* ORM */
        environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        /* Get EnvConfig */
        EnvConfig envConfig = EnvConfig.getRuntimeEnvConfig();
        if (envConfig == null) {
            g_Logger.error("envConfig is null");
        } else {
              /* Swagger Config */
            BeanConfig beanConfig = new BeanConfig();
            beanConfig.setTitle(envConfig.getSERVICE_NAME());
            beanConfig.setVersion(envConfig.getAPI_VERSION());
            beanConfig.setBasePath("/" + envConfig.getARTIFACT_ID().toLowerCase());
            /* Scheme Definition */
            //beanConfig.setSchemes(new String[]{"http","https"});

            /* Security Definition */
            //beanConfig.getSwagger().addSecurityDefinition("jwt_header", new ApiKeyAuthDefinition("access_token", In.HEADER));
            //beanConfig.getSwagger().addSecurity(new SecurityRequirement());
            /* Auto Fill  */
            beanConfig.setResourcePackage(baseConfiguration.getSwaggerBundleConfiguration().getResourcePackage());
            beanConfig.setScan(true);
        }

    }


}
