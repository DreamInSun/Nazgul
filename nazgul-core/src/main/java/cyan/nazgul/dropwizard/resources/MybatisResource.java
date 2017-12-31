package cyan.nazgul.dropwizard.resources;

import cyan.nazgul.dropwizard.DbConfiguration;
import cyan.nazgul.dropwizard.container.GlobalInstance;
import io.dropwizard.setup.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by DreamInSun on 2016/7/22.
 */
@SuppressWarnings("unchecked")
public class MybatisResource<TConfig extends DbConfiguration> extends BaseResource<TConfig> {

    /*========== Static Properties ==========*/

    protected SqlSessionFactory getSqlSessionFactory() {
        return GlobalInstance.getSqlSessionFactory();
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
