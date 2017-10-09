package cyan.nazgul.dropwizard.resources;

import cyan.nazgul.dropwizard.DbConfiguration;
import io.dropwizard.setup.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by DreamInSun on 2016/7/22.
 */
public class MybatisResource<TConfig extends DbConfiguration> extends BaseResource<TConfig> {

    /*========== Static Properties ==========*/
    protected static SqlSessionFactory g_sqlSessionFactory;

    public static void setSqlSessionFactory(SqlSessionFactory g_sessionFactory) {
        MybatisResource.g_sqlSessionFactory = g_sessionFactory;
    }

    protected SqlSessionFactory getSqlSessionFactory() {
        return MybatisResource.g_sqlSessionFactory;
    }

    /*========== Properties ==========*/
    //protected Map<Class, Object> m_DaoMap = new HashMap<>();

    /*========== Constructor ==========*/
    public MybatisResource(TConfig config, Environment environment) {
        super(config, environment);
    }


    /*========== Assistant ==========*/

    /**
     * 快速打开一个SqlSession并获得实体的Mapper。
     *
     * @param tMapperClass
     * @param <TMapper>
     * @return
     */
    public <TMapper> TMapper getDbMapper(Class<TMapper> tMapperClass) {
        TMapper entityMpr = null;
        try (SqlSession session = this.getSqlSessionFactory().openSession()) {
            entityMpr = session.getMapper(tMapperClass);
        } catch (Exception e) {
            this.getLogger().info("MyBatis openSession Error.", e);
        }
        return entityMpr;
    }
}
