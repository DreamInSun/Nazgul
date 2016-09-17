package cyan.nazgul.dropwizard.component;

import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by DreamInSun on 2016/7/21.
 */
public class WebComponent<TConfig extends BaseConfiguration> implements IComponent<TConfig> {

    /*========== Constructor ==========*/
    public WebComponent() {

    }

    /*========== Interface : IComponent ==========*/
    @Override
    public void init(Bootstrap bootstrap) {
        String projectBase = EnvConfig.getRuntimeEnvConfig().getARTIFACT_ID().toLowerCase();
        String basePath = "/" + projectBase + "/web";
         /*===== Assets Bundle =====*/
        bootstrap.addBundle(new AssetsBundle("/web/", basePath + "/", "index.html"));
        bootstrap.addBundle(new AssetsBundle("/web/css", basePath + "/css", null, "css"));
        bootstrap.addBundle(new AssetsBundle("/web/js", basePath + "/js", null, "js"));
        bootstrap.addBundle(new AssetsBundle("/web/fonts", basePath + "/fonts", null, "fonts"));
    }

    @Override
    public void postInit(EnvConfig envConfig, Bootstrap<TConfig> bootstrap) {

    }

    @Override
    public void run(TConfig config, Environment environment) {

    }


}
