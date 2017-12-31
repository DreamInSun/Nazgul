package cyan.nazgul.dropwizard.component;

import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * 自定义附件注册组件
 * Created by DreamInSun on 2016/7/21.
 */
@SuppressWarnings("unchecked")
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
