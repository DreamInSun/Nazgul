package cyan.nazgul.dropwizard.component;

import cyan.nazgul.dropwizard.BaseConfiguration;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by DreamInSun on 2016/7/22.
 */
public class MultipartComponent<TConfig extends BaseConfiguration> implements IComponent<TConfig> {
    @Override
    public void init(Bootstrap bootstrap) {
        bootstrap.addBundle(new MultiPartBundle());
    }

    @Override
    public void run(TConfig config, Environment environment) {

    }
}