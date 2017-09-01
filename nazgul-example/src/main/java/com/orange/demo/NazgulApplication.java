package com.orange.demo;

import cyan.nazgul.dropwizard.DbApplication;
import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SwaggerDefinition(
        info = @Info(
                description = "orange.core.Downloads",
                version = "0.1.0",
                title = "应用下载管理服务",
                termsOfService = "share and care",
                contact = @Contact(name = "DreamInSun", email = "yancy_chen@hotmail.com", url = "http://cyan.org.cn"),
                license = @License(name = "Apache 2.0", url = "http://www.apache.org")
        )
)
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
