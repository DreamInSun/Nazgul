package cyan.nazgul.dropwizard.cli;

import cyan.nazgul.dropwizard.DbConfiguration;
import io.dropwizard.db.DatabaseConfiguration;
import io.dropwizard.flyway.FlywayConfiguration;
import io.dropwizard.flyway.cli.DbCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DreamInSun on 2016/7/18.
 */
public class FlywayCommand<T extends DbConfiguration> extends DbCommand<T> {
    private static final Logger g_Logger = LoggerFactory.getLogger(FlywayCommand.class);

    /*========== Constructor ========== */
    public FlywayCommand(final DatabaseConfiguration<T> databaseConfiguration,
                         final FlywayConfiguration<T> flywayConfiguration,
                         final Class<T> configurationClass) {
        super(databaseConfiguration, flywayConfiguration, configurationClass);
    }

}

