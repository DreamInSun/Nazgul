package cyan.nazgul.dropwizard.bundle;

import cyan.nazgul.dropwizard.DbConfiguration;
import io.dropwizard.Bundle;
import io.dropwizard.Configuration;
import io.dropwizard.db.DatabaseConfiguration;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.flyway.FlywayConfiguration;
import io.dropwizard.flyway.FlywayFactory;
import io.dropwizard.flyway.cli.DbCommand;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.util.Generics;

/**
 * Created by DreamInSun on 2016/7/22.
 */
public class FlywayBundle<T extends DbConfiguration> implements Bundle, DatabaseConfiguration<T>, FlywayConfiguration<T> {

    /*========== Properties =========*/
    protected Class<T> m_klass;

    /*========== Construcotr =========*/
    public FlywayBundle(Class klass) {
        m_klass = klass;
    }

    @Override
    public final void initialize(final Bootstrap<?> bootstrap) {
        if (m_klass == null) {
            m_klass = Generics.getTypeParameter(getClass(), Configuration.class);
        }
        bootstrap.addCommand(new DbCommand<T>(this, this, m_klass));
    }

    @Override
    public final void run(final Environment environment) {
        // nothing doing
    }

    /*========== Interface :  ==========*/
    @Override
    public FlywayFactory getFlywayFactory(T configuration) {
        return configuration.getFlywayFactory(configuration);
    }

    @Override
    public PooledDataSourceFactory getDataSourceFactory(T config) {
        return config.getDataSourceFactory();
    }
}
