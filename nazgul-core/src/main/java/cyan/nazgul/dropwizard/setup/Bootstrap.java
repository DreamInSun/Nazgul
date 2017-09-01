package cyan.nazgul.dropwizard.setup;

import io.dropwizard.Application;
import io.dropwizard.Configuration;

/**
 * Created by DreamInSun on 2016/9/20.
 */
public class Bootstrap<T extends Configuration> extends io.dropwizard.setup.Bootstrap<T> {

    /**
     * Creates a new {@link Bootstrap} for the given application.
     *
     * @param application a Dropwizard {@link Application}
     */
    public Bootstrap(Application<T> application) {
        super(application);
    }
}
