package cyan.nazgul.dropwizard.resources;

import com.querydsl.jpa.impl.JPAQueryFactory;
import cyan.nazgul.dropwizard.DbConfiguration;
import cyan.nazgul.dropwizard.container.GlobalInstance;
import io.dropwizard.setup.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * 在MybatisResource的基础上增加了EntityManager，支持JPA
 * 共享使用JDBC连接。
 * Created by DreamInSun on 2016/7/22.
 */
public class DbResource<TConfig extends DbConfiguration> extends MybatisResource<TConfig> {

    /*========== Static Properties ==========*/
    protected static EntityManager g_EntityManager;

    public EntityManager getEntityManager() {
        return  this.getEntityManagerFactory().createEntityManager();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return GlobalInstance.getEntityManagerFactory();
    }

    /*========== Properties ==========*/
    protected static JPAQueryFactory g_queryFactory;

    public JPAQueryFactory getQueryFactory() {
        if (g_queryFactory == null) {
            g_queryFactory = new JPAQueryFactory(this.getEntityManager());
        }
        return g_queryFactory;
    }

    public ApplicationContext getSpringContext() {
        return GlobalInstance.getSpringContext();
    }

    /*========== Constructor ==========*/
    public DbResource(TConfig config, Environment environment) {
        super(config, environment);

    }

}
