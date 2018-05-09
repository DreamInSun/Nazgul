package cyan.nazgul.dropwizard.component;

import com.codahale.metrics.MetricRegistry;
import com.google.common.collect.Maps;
import com.loginbox.dropwizard.mybatis.types.*;
import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.DbConfiguration;
import cyan.nazgul.dropwizard.container.GlobalInstance;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

/**
 * 自定义附件注册组件
 * Created by DreamInSun on 2016/7/21.
 */
@SuppressWarnings("unchecked")
public class MultipleDataSourceComponent<TConfig extends DbConfiguration> implements IComponent<TConfig> {
    public static final Logger g_logger = org.slf4j.LoggerFactory.getLogger(MultipleDataSourceComponent.class);

    /*========== Static Properties ==========*/
    private static Map<String, SqlSessionFactory> g_SqlSessionFactoryMap = Maps.newHashMap();
    private static String g_rootPackage;

    /*========== Constructor ==========*/
    public MultipleDataSourceComponent(String rootPackage) {
        GlobalInstance.setSqlSessionFactoryMap(g_SqlSessionFactoryMap);
        g_rootPackage = rootPackage;

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
        Map<String, DataSourceFactory> databaseMap = config.getDatabaseMap();
        if (databaseMap != null) {
            g_logger.info("/*========== Enable Multiple Database Source Mode ==========*/");
            for (Map.Entry<String, DataSourceFactory> entry : databaseMap.entrySet()) {
                String databaseName = entry.getKey();
                g_logger.info("Register DataSource ：" + databaseName);
                try {
                    DataSourceFactory dataSourceFactory = entry.getValue();
                /*===== DataSourceFactory Modified =====*/
                    //若没填写JDBC驱动类，则使用默认的驱动类
                    if (dataSourceFactory.getDriverClass() == null) {
                        dataSourceFactory.setDriverClass(config.getDataSourceFactory().getDriverClass());
                    }
                /*=====  =====*/
                    SqlSessionFactory sqlSessionFactory = buildSqlSessionFactory(databaseName, dataSourceFactory, environment.metrics());
                    registerSqlSessionFactory(databaseName, sqlSessionFactory);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void registerSqlSessionFactory(String databaseName, SqlSessionFactory sqlSessionFactory) {
        g_SqlSessionFactoryMap.put(databaseName, sqlSessionFactory);
    }

    /*========== Assistant Function ==========*/
    protected SqlSessionFactory buildSqlSessionFactory(String databaseName, DataSourceFactory dataSourceFactory, MetricRegistry metricRegistry) throws Exception {
        /*=====  =====*/
        //GlobalInstance.getSqlSessionFactory().
        DataSource dataSource = dataSourceFactory.build(metricRegistry, databaseName);
        Configuration configuration = buildMybatisConfiguration(databaseName, dataSource);
        /*===== Fill Configuration =====*/
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }

    /**
     * 基于DataSource创建一个
     *
     * @param databaseName
     * @param dataSource
     * @return
     * @throws Exception
     */
    protected Configuration buildMybatisConfiguration(String databaseName, DataSource dataSource) throws Exception {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        org.apache.ibatis.mapping.Environment mybatisEnvironment = new org.apache.ibatis.mapping.Environment(databaseName, transactionFactory, dataSource);
        Configuration configuration = new Configuration(mybatisEnvironment);
        registerTypeHandlers(configuration.getTypeHandlerRegistry());

        configuration.addMappers(g_rootPackage + ".mappers");
        return configuration;
    }

    private void registerTypeHandlers(TypeHandlerRegistry registry) {
        registry.register(UUID.class, new UuidTypeHandler());
        registry.register(java.util.Optional.class, new Java8OptionalTypeHandler());
        registry.register(com.google.common.base.Optional.class, new GuavaOptionalTypeHandler());
        registry.register(URL.class, new UrlTypeHandler());
        registry.register(URI.class, new UriTypeHandler());
    }


}
