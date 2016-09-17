package cyan.nazgul.dropwizard.resources;

import cyan.nazgul.dropwizard.DbConfiguration;
import io.dropwizard.setup.Environment;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by DreamInSun on 2016/7/22.
 */
public class MybatisResource<TConfig extends DbConfiguration> extends BaseResource<TConfig> {

    /*========== Static Properties ==========*/
    protected static SqlSessionFactory g_sessionFactory;

    public static void setG_sessionFactory(SqlSessionFactory g_sessionFactory) {
        MybatisResource.g_sessionFactory = g_sessionFactory;
    }

    protected SqlSessionFactory getSqlSessionFactory() {
        return MybatisResource.g_sessionFactory;
    }

    /*========== Properties ==========*/
    //protected Map<Class, Object> m_DaoMap = new HashMap<>();

    /*========== Constructor ==========*/
    public MybatisResource(TConfig config, Environment environment) {
        super(config, environment);
    }
}
