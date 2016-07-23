package cyan.nazgul.dropwizard.component;

import cyan.nazgul.dropwizard.BaseConfiguration;
import cyan.nazgul.dropwizard.health.Database;
import cyan.nazgul.dropwizard.health.DatabaseHealthCheck;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by DreamInSun on 2016/7/21.
 */
public class DbHealthComponent<TConfig extends BaseConfiguration> implements IComponent<TConfig> {
    @Override
    public void init(Bootstrap bootstrap) {

    }

    @Override
    public void run(TConfig config, Environment environment) {
        final Database database = new Database();
        environment.healthChecks().register("database", new DatabaseHealthCheck(database));

    }
}
