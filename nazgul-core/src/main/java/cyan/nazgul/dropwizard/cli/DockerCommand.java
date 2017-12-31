package cyan.nazgul.dropwizard.cli;

import cyan.nazgul.dropwizard.DbConfiguration;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.cli.EnvironmentCommand;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.flyway.FlywayFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.eclipse.jetty.util.component.LifeCycle;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Docker 启动命令处理组件
 * Created by DreamInSun on 2016/7/18.
 */
@SuppressWarnings("unchecked")
public class DockerCommand<T extends Configuration> extends EnvironmentCommand<T> {
    private static final Logger g_logger = LoggerFactory.getLogger(DockerCommand.class);

    /*========== Static Properties ==========*/
    private static Bootstrap<?> g_bootstrap = null;

    /*========== Properties ==========*/
    private final Class<T> configurationClass;

    /*========== Getter & Setter ==========*/
    /*
     * Since we don't subclass ServerCommand, we need a concrete reference to the configuration
     * class.
     */
    @Override
    protected Class<T> getConfigurationClass() {
        return configurationClass;
    }

    /*========== Constructor ==========*/
    public DockerCommand(Application<T> application) {
        super(application, "docker", "Runs as an HTTP server application in docker mode.");
        this.configurationClass = application.getConfigurationClass();

        /* SubCommand */

    }

    @Override
    public void configure(Subparser subparser) {
        super.configure(subparser);
        /*===== Command Option =====*/
        subparser.addArgument("--auto-migrate").dest("auto-migrate").type(Boolean.class).setDefault(false).required(false).help("Auto run db migrate");
        subparser.addArgument("--debug").dest("debug").type(Boolean.class).setDefault(false).required(false).help("Run on debug mode");
    }

    @Override
    protected void run(Environment environment, Namespace namespace, T configuration) throws Exception {
        /*===== Database Migrate =====*/
        if (true == namespace.getBoolean("auto-migrate")) {
            g_logger.info("\r\n\r\n/*========== Migrate Database ==========*/\r\n");
            if (configuration instanceof DbConfiguration) {
                /*===== Get Database =====*/
                DbConfiguration config = (DbConfiguration) configuration;
                /*===== Init Flyway =====*/
                final PooledDataSourceFactory datasourceFactory = config.getDataSourceFactory();
                final FlywayFactory flywayFactory = config.getFlywayFactory(configuration);
                final Flyway flyway = flywayFactory.build(datasourceFactory.build(g_bootstrap.getMetricRegistry(), "Flyway"));
                flyway.migrate();
            }
        }
        /*===== Staty Server =====*/
        final Server server = configuration.getServerFactory().build(environment);
        try {
            server.addLifeCycleListener(new LifeCycleListener());
            cleanupAsynchronously();
            server.start();
        } catch (Exception e) {
            g_logger.error("Unable to start server, shutting down", e);
            server.stop();
            cleanup();
            throw e;
        }
    }


    /*========== Override : ConfiguredCommand ==========*/
    @Override
    public void run(Bootstrap<?> bootstrap, Namespace namespace) throws Exception {
        g_bootstrap = bootstrap;
        /*===== Run Command =====*/
        super.run(bootstrap, namespace);
    }


    /*========== Life Cycle Control ==========*/
    private class LifeCycleListener extends AbstractLifeCycle.AbstractLifeCycleListener {
        @Override
        public void lifeCycleStopped(LifeCycle event) {
            cleanup();
        }
    }
}

