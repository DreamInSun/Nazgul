package cyan.svc.nazgulexample;

import cyan.nazgul.dropwizard.DbApplication;
import cyan.nazgul.dropwizard.component.JobComponent;
import cyan.nazgul.dropwizard.component.ShiroComponent;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
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
public class Application<TConfig extends Configuration> extends DbApplication<TConfig> {
    /*========== Static Properties ==========*/
    static final Logger g_Logger = LoggerFactory.getLogger(Application.class);

    /*========== Constructor ==========*/
    public Application(String[] args) {
        super(args, Configuration.class);
        /*===== Optional Component =====*/
        m_CompList.add(new JobComponent<>(g_classRoot));
        m_CompList.add(new ShiroComponent<>());
        //m_CompList.add(new WebSocketComponent<>(g_classRoot));
    }

    /*========== Application Initialization ==========*/
    @Override
    public void initialize(Bootstrap<TConfig> bootstrap) {
        super.initialize(bootstrap);
        /*===== Assets Bundle =====*/
        //在这里初始化阶段添加其他的组件
        //bootstrap.addBundle(new AssetsBundle("/assets/", "/assets"));

        //添加其他的服务线程

    }

    /*========== Application Run ==========*/
    @Override
    public void run(TConfig config, Environment environment) throws Exception {
        super.run(config, environment);
    }

    /*========== Application Entrance ==========*/
    public static void main(String[] args) throws Exception {
        new Application<>(args).run();
    }
}
