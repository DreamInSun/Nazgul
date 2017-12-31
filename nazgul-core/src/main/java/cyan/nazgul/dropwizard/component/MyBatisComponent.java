package cyan.nazgul.dropwizard.component;

import com.loginbox.dropwizard.mybatis.MybatisBundle;
import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.DbConfiguration;
import cyan.nazgul.dropwizard.container.GlobalInstance;
import cyan.nazgul.dropwizard.resources.MybatisResource;
import cyan.nazgul.mngr.MybatisMngr;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * MyBatic的启动组件
 * Created by DreamInSun on 2016/7/21.
 */
@SuppressWarnings("unchecked")
public class MyBatisComponent<TConfig extends DbConfiguration> implements IComponent<TConfig> {

    /*========== Constructor ==========*/
    private MybatisBundle<TConfig> mybatisBundle;

    /*========== Constructor ==========*/
    public MyBatisComponent(String packagePath) {
        mybatisBundle = new MybatisBundle<TConfig>(packagePath) {
            @Override
            public DataSourceFactory getDataSourceFactory(TConfig configuration) {
                return configuration.getDataSourceFactory();
            }
        };
    }

    /*========== Interface : IComponent ==========*/
    public void init(Bootstrap bootstrap) {
        bootstrap.addBundle(mybatisBundle);

    }

    public void postInit(EnvConfig envConfig, Bootstrap<TConfig> bootstrap) {

    }

    public void run(TConfig config, Environment environment) {
        GlobalInstance.setSqlSessionFactory(mybatisBundle.getSqlSessionFactory());
    }


}
