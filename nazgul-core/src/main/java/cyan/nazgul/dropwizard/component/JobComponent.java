package cyan.nazgul.dropwizard.component;

import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import de.spinscale.dropwizard.jobs.JobsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * @see <a>https://github.com/spinscale/dropwizard-jobs</a>
 * Created by DreamInSun on 2017/9/15.
 */
public class JobComponent<TConfig extends BaseConfiguration> implements IComponent<TConfig> {
    /*========== Constructor ==========*/
    private JobsBundle jobsBunlde;

    /*========== Constructor ==========*/
    public JobComponent(String packagePath) {
        jobsBunlde = new JobsBundle() {

        };
    }

    /*========== Interface : IComponent ==========*/
    @Override
    public void init(Bootstrap bootstrap) {
        bootstrap.addBundle(jobsBunlde);
    }

    @Override
    public void postInit(EnvConfig envConfig, Bootstrap<TConfig> bootstrap) {
        //TODO
    }

    @Override
    public void run(TConfig config, Environment environment) {
        //TODO
    }
}
