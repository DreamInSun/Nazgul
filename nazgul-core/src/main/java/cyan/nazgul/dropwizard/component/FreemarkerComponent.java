package cyan.nazgul.dropwizard.component;

import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DreamInSun on 2016/7/21.
 */
public class FreemarkerComponent<TConfig extends BaseConfiguration> implements IComponent<TConfig> {


    protected class FreemarkerViewBundle extends ViewBundle<TConfig> {
        @Override
        public Map<String, Map<String, String>> getViewConfiguration(TConfig configuration) {
            Map<String, Map<String, String>> configMap =
                    (Map<String, Map<String, String>>)
                            new HashMap().put(".ftl", new HashMap<String, String>().put("strict_syntax", "yes"));
            return configMap;
        }
    }

    /*========== Interface : IComponent ==========*/
    @Override
    public void init(Bootstrap bootstrap) {
        bootstrap.addBundle(new FreemarkerViewBundle());
    }

    @Override
    public void postInit(EnvConfig envConfig, Bootstrap<TConfig> bootstrap) {

    }

    @Override
    public void run(TConfig config, Environment environment) {

    }
}
