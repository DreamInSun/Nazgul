package cyan.nazgul.dropwizard.container;

import com.google.common.collect.Lists;
import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import cyan.nazgul.dropwizard.auth.multiauth.MultiAuthFilter;
import io.dropwizard.auth.AuthValueFactoryProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Map;

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

    private static Map<String, SqlSessionFactory> sqlSessionFactoryMap;

    private static List<MultiAuthFilter> authFilterList = Lists.newArrayList();

    public static List<Class<? extends AuthValueFactoryProvider.Binder>> getAuthBinderList() {
        return authBinderList;
    }

    public static void setAuthBinderList(List<Class<? extends AuthValueFactoryProvider.Binder>> authBinderList) {
        GlobalInstance.authBinderList = authBinderList;
    }

    private static List<Class<? extends AuthValueFactoryProvider.Binder>> authBinderList = Lists.newArrayList();

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

    public static Map<String, SqlSessionFactory> getSqlSessionFactoryMap() {
        return sqlSessionFactoryMap;
    }

    public static void setSqlSessionFactoryMap(Map<String, SqlSessionFactory> sqlSessionFactoryMap) {
        GlobalInstance.sqlSessionFactoryMap = sqlSessionFactoryMap;
    }

    public static List<MultiAuthFilter> getAuthFilterList() {
        if (authFilterList == null) {
            authFilterList = Lists.newArrayList();
        }
        return authFilterList;
    }

    public static void setAuthFilterList(List<MultiAuthFilter> g_authFilter) {
        GlobalInstance.authFilterList = g_authFilter;
    }
}
