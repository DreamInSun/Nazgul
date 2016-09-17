package cyan.nazgul.dropwizard.component;

import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


/**
 * Created by DreamInSun on 2016/7/21.
 */
public interface IComponent<TConfig extends BaseConfiguration> {

    /**
     * Invoked in Dropwizard Application initialize()
     *
     * @param bootstrap
     */
    void init(Bootstrap<TConfig> bootstrap);

    /**
     * After Enviroment is Initialized
     * Invoked in Dropwizard Application initialize()
     *
     * @param bootstrap
     */
    void postInit(EnvConfig envConfig, Bootstrap<TConfig> bootstrap);

    /**
     * Invoked in Dropwizard Application run()
     *
     * @param config
     * @param environment
     */
    void run(TConfig config, Environment environment);
}
