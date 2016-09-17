package cyan.nazgul.dropwizard.component;

import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.DbConfiguration;
import cyan.nazgul.dropwizard.bundle.FlywayBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by DreamInSun on 2016/7/21.
 */
public class FlywayComponent<TConfig extends DbConfiguration> implements IComponent<TConfig> {

    protected Class m_klass;

    public FlywayComponent(Class klass) {
        m_klass = klass;
    }

    @Override
    public void init(Bootstrap bootstrap) {

        bootstrap.addBundle(new FlywayBundle<TConfig>(m_klass));
    }

    @Override
    public void postInit(EnvConfig envConfig, Bootstrap<TConfig> bootstrap) {

    }

    @Override
    public void run(TConfig config, Environment environment) {

    }
}
