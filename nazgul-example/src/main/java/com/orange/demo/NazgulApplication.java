package com.orange.demo;

import com.orange.demo.resources.HelloWorldResource;
import com.orange.demo.resources.PersonResource;
import com.orange.demo.resources.UserResource;
import cyan.nazgul.dropwizard.DbApplication;
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
//    @Override
//    public void initialize(Bootstrap<TConfig> bootstrap) {
//        super.initialize(bootstrap);
//        /*===== Assets Bundle =====*/
//        bootstrap.addBundle(new AssetsBundle("/assets/", "/assets"));
//    }

    /*========== Application Run ==========*/
//    @Override
//    public void run(TConfig config, Environment environment) throws Exception {
//        super.run(config, environment);
//    }

    /*========== Application Entrance ==========*/
    public static void main(String[] args) throws Exception {
        new NazgulApplication<>(args).run();
    }
}
