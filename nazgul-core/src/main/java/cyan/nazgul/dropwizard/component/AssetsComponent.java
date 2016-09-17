package cyan.nazgul.dropwizard.component;

import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import cyan.nazgul.dropwizard.auth.ExampleAuthenticator;
import cyan.nazgul.dropwizard.auth.ExampleAuthorizer;
import cyan.nazgul.dropwizard.auth.User;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

/**
 * Created by DreamInSun on 2016/7/21.
 */
public class AssetsComponent<TConfig extends BaseConfiguration> implements IComponent<TConfig> {

    /*========== Constructor ==========*/
    public AssetsComponent() {

    }

    /*========== Interface : IComponent ==========*/
    @Override
    public void init(Bootstrap bootstrap) {
        String projectBase = EnvConfig.getRuntimeEnvConfig().getARTIFACT_ID().toLowerCase();
        String basePath = "/" + projectBase + "/assets";
         /*===== Assets Bundle =====*/
        bootstrap.addBundle(new AssetsBundle("/assets/", basePath + "/", "index.html"));

        bootstrap.addBundle(new AssetsBundle("/assets/css", basePath + "/css", null, "css"));
        bootstrap.addBundle(new AssetsBundle("/assets/js", basePath + "/js", null, "js"));
        bootstrap.addBundle(new AssetsBundle("/assets/fonts", basePath + "/fonts", null, "fonts"));
    }

    @Override
    public void postInit(EnvConfig envConfig, Bootstrap<TConfig> bootstrap) {

    }

    @Override
    public void run(TConfig config, Environment environment) {

    }


}
