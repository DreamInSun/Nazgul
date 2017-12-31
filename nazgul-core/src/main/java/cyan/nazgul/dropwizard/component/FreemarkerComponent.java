package cyan.nazgul.dropwizard.component;

import com.google.common.collect.Maps;
import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import java.util.Map;

/**
 * Freemarker的启动组件
 * Created by DreamInSun on 2016/7/21.
 */
@SuppressWarnings("unchecked")
public class FreemarkerComponent<TConfig extends BaseConfiguration> implements IComponent<TConfig> {

    protected class FreemarkerViewBundle extends ViewBundle<TConfig> {
        @Override
        public Map<String, Map<String, String>> getViewConfiguration(TConfig configuration) {
            /* */
            Map<String, String> ftlMap = Maps.newHashMap();
            ftlMap.put("strict_syntax", "yes");
            /* */
            Map<String, Map<String, String>> configMap = Maps.newHashMap();
            configMap.put(".ftl", ftlMap);
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
