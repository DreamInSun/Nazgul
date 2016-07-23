package com.orange.demo;

import com.orange.demo.resources.UserResource;
import com.orange.demo.resources.HelloWorldResource;
import com.orange.demo.resources.PersonResource;
import cyan.nazgul.dropwizard.DbApplication;
import cyan.nazgul.dropwizard.manage.RiakClient;
import cyan.nazgul.dropwizard.manage.RiakClientManager;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NazgulApplication<TConfig extends NazgulConfiguration> extends DbApplication<TConfig> {
    /*========== Static Properties ==========*/
    static final Logger g_Logger = LoggerFactory.getLogger(NazgulApplication.class);

    /*========== Constructor ==========*/
    public NazgulApplication(String[] args) {
        super(args, NazgulConfiguration.class);
    }

    /*========== Application Initialization ==========*/
    @Override
    public void initialize(Bootstrap<TConfig> bootstrap) {
        super.initialize(bootstrap);
        /*===== Assets Bundle =====*/
        bootstrap.addBundle(new AssetsBundle("/assets/", "/assets"));
    }

    /*========== Application Run ==========*/
    @Override
    public void run(TConfig config, Environment environment) throws Exception {
        super.run(config, environment);
        /*===== Manage =====*/
        RiakClient client = new RiakClient();
        RiakClientManager riakClientManager = new RiakClientManager(client);
        environment.lifecycle().manage(riakClientManager);
        /*===== Task =====*/
        //environment.admin().addTask(new TruncateDatabaseTask(database));
        /*===== Register Resources =====*/
        environment.jersey().register(new UserResource(config, environment));
        environment.jersey().register(new HelloWorldResource(config, environment));
        environment.jersey().register(new PersonResource(config, environment));
    }

    /*========== Application Entrance ==========*/
    public static void main(String[] args) throws Exception {
        new NazgulApplication<>(args).run();
    }
}
