package cyan.nazgul.dropwizard.component;

import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import cyan.nazgul.dropwizard.manage.RiakClient;
import cyan.nazgul.dropwizard.manage.RiakClientManager;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by DreamInSun on 2016/7/21.
 */
public class RiakComponent<TConfig extends BaseConfiguration> implements IComponent<TConfig> {

    /*========== Constructor ==========*/
    public RiakComponent() {

    }

    /*========== Interface : IComponent ==========*/
    @Override
    public void init(Bootstrap bootstrap) {

    }

    @Override
    public void postInit(EnvConfig envConfig, Bootstrap<TConfig> bootstrap) {


    }

    @Override
    public void run(TConfig config, Environment environment) {
        /*===== Manage =====*/
        RiakClient client = new RiakClient();
        RiakClientManager riakClientManager = new RiakClientManager(client);
        environment.lifecycle().manage(riakClientManager);

    }


}
