package cyan.nazgul.dropwizard.cli;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.cli.EnvironmentCommand;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.eclipse.jetty.util.component.LifeCycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DreamInSun on 2016/7/18.
 */
public class DockerCommand<T extends Configuration> extends EnvironmentCommand<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DockerCommand.class);

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
    }

    @Override
    protected void run(Environment environment, Namespace namespace, T configuration) throws Exception {
        final Server server = configuration.getServerFactory().build(environment);
        try {
            server.addLifeCycleListener(new LifeCycleListener());
            cleanupAsynchronously();
            server.start();
        } catch (Exception e) {
            LOGGER.error("Unable to start server, shutting down", e);
            server.stop();
            cleanup();
            throw e;
        }
    }


    /*========== Override : ConfiguredCommand ==========*/
    @Override
    public void run(Bootstrap<?> bootstrap, Namespace namespace) throws Exception {
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

