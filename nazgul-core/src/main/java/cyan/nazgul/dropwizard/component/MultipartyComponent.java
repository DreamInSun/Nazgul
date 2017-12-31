package cyan.nazgul.dropwizard.component;

import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * JAX-RS支持 Multipart的启动组件
 * Created by DreamInSun on 2016/9/20.
 */
@SuppressWarnings("unchecked")
public class MultipartyComponent<TConfig extends BaseConfiguration> implements IComponent<TConfig> {
    @Override
    public void init(Bootstrap bootstrap) {
        bootstrap.addBundle(new MultiPartBundle());
    }

    @Override
    public void postInit(EnvConfig envConfig, Bootstrap<TConfig> bootstrap) {

    }

    @Override
    public void run(TConfig config, Environment environment) {
//        environment.jersey().register(MultiPartFeature.class);
//        environment.jersey().register(MultiPartConfigProvider.class);
    }
}
