package cyan.nazgul.dropwizard.component;

import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.DbConfiguration;
import cyan.nazgul.dropwizard.config.SpringConfiguration;
import cyan.nazgul.dropwizard.container.GlobalInstance;
import cyan.nazgul.dropwizard.spring.SpringContextBuilder;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.springframework.context.ApplicationContext;

/**
 * Dropwizard集成Spring组件
 *
 * @see <a>http://www.fortitudetec.com/blogs/2015/8/10/easily-integrate-spring-into-dropwizard</a>
 *
 * Created by DreamInSun on 2017/12/29.
 */
@SuppressWarnings("unchecked")
public class SpringContextComponent<TConfig extends DbConfiguration> implements IComponent<TConfig> {

    /*========== Constructor ==========*/
    public SpringContextComponent() {

    }

    /*========== Interface : IComponent ==========*/
    @Override
    public void init(Bootstrap bootstrap) {

    }

    @Override
    public void postInit(EnvConfig envConfig, Bootstrap<TConfig> bootstrap) {

    }

    @Override
    public void run(TConfig config, Environment environment) {
        DataSourceFactory dataSourceFactory = config.getDataSourceFactory();
        ManagedDataSource dataSource = dataSourceFactory.build(environment.metrics(), "dataSource");

        ApplicationContext context = new SpringContextBuilder()
                .addParentContextBean("dataSource", dataSource)
                .addParentContextBean("dropwizardConfiguration", config)
                //.withAnnotationConfigurations(AcmeCoreSpringConfiguration.class, AcmeDataSpringConfiguration.class, AcmeSecuritySpringConfiguration.class)
                .withXmlConfigLocations("spring/appContext-core.xml", "spring/appContext-data.xml", "spring/appContext-security.xml")
                .build();

        GlobalInstance.setSpringContext(context);
    }

}
