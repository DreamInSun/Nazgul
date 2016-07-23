package cyan.nazgul.dropwizard.component;

import com.loginbox.dropwizard.mybatis.MybatisBundle;
import cyan.nazgul.dropwizard.DbConfiguration;
import cyan.nazgul.dropwizard.resources.MybatisResource;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by DreamInSun on 2016/7/21.
 */
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
    @Override
    public void init(Bootstrap bootstrap) {
        bootstrap.addBundle(mybatisBundle);

    }


    @Override
    public void run(TConfig config, Environment environment) {
        MybatisResource.setG_sessionFactory(mybatisBundle.getSqlSessionFactory());
    }


}
