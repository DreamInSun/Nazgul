package cyan.svc.nazgulexample;

import cyan.nazgul.dropwizard.DbApplication;
import cyan.nazgul.dropwizard.component.JwtComponent;
import cyan.nazgul.dropwizard.component.JobComponent;
import cyan.nazgul.dropwizard.component.ShiroComponent;
import cyan.svc.EntityOutput;
import cyan.svc.nazgulexample.contract.ErrCode;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SwaggerDefinition(
        info = @Info(
                description = "cyan.core.NazgulExample",
                version = "1.3.3",
                title = "Nazgul快发开发框架脚手架",
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
        m_CompList.add(new JwtComponent<>());
        //m_CompList.add(new WebSocketComponent<>(g_classRoot));
        /*===== Register ErrorInfoMap =====*/
        //TODO 可以用其他版本的错误表代替内置错误表
        EntityOutput.setErrInfoMapping(ErrCode.getDefaultErrInfoMapper(ErrCode.class));
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
