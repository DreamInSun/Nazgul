package cyan.nazgul.dropwizard.container;

import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * 应用全局实例组件
 * Created by DreamInSun on 2017/10/30.
 */
public class GlobalInstance {

    /*========== Static Properties ==========*/
    private static BaseConfiguration configuration;

    private static SqlSessionFactory sqlSessionFactory;

    private static EntityManagerFactory entityManagerFactory;

    private static EntityManager entityManager;

    private static EnvConfig envConfig;

    private static ApplicationContext springContext;

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

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public static void setEntityManager(EntityManager entityManager) {
        GlobalInstance.entityManager = entityManager;
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public static void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        GlobalInstance.entityManagerFactory = entityManagerFactory;
    }

    public static ApplicationContext getSpringContext() {
        return springContext;
    }

    public static void setSpringContext(ApplicationContext springContext) {
        GlobalInstance.springContext = springContext;
    }
}
