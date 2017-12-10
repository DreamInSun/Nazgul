package cyan.nazgul.dropwizard.container;

import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by DreamInSun on 2017/10/30.
 */
public class GlobalInstance {

    /*========== Static Properties ==========*/
    private static BaseConfiguration configuration;

    private static SqlSessionFactory sqlSessionFactory;

    private static EnvConfig envConfig;

    /*========== Getter & Setter ==========*/
    public static BaseConfiguration getConfiguration() {
        return configuration;
    }

    public static void setConfiguration(BaseConfiguration configuration) {
        GlobalInstance.configuration = configuration;
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public static void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        GlobalInstance.sqlSessionFactory = sqlSessionFactory;
    }

    public static EnvConfig getEnvConfig() {
        return envConfig;
    }

    public static void setEnvConfig(EnvConfig envConfig) {
        GlobalInstance.envConfig = envConfig;
    }

}
