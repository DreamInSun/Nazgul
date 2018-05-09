package cyan.nazgul.dropwizard.resources;

import cyan.nazgul.dropwizard.DbConfiguration;
import cyan.nazgul.dropwizard.container.GlobalInstance;
import io.dropwizard.setup.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Map;

/**
 * Created by DreamInSun on 2016/7/22.
 * MyBatis 的操作支持
 * 在不启动Hibernate的模式下使用，若启用Hibernate，则使用DbResource
 */
@SuppressWarnings("unchecked")
public class MybatisResource<TConfig extends DbConfiguration> extends BaseResource<TConfig> {


    /*========== SqlSessionFactory ==========*/
    protected SqlSessionFactory getSqlSessionFactory() {
        return GlobalInstance.getSqlSessionFactory();
    }

    /**
     * 注意該方案需要自行調用session.close()
     *
     * @return
     */
    public SqlSession getSqlSession() {
        return this.getSqlSessionFactory().openSession();
    }

    protected SqlSessionFactory getSqlSessionFactory(String databaseName) {
        SqlSessionFactory sqlSessionFactory = null;
        /*=====  =====*/
        Map<String, SqlSessionFactory> map = GlobalInstance.getSqlSessionFactoryMap();
        /*=====  =====*/
        if (map != null) {
            sqlSessionFactory = map.get(databaseName);
        }
        /*=====  =====*/
        return sqlSessionFactory;
    }

    /**
     * 注意該方案需要自行調用session.close()
     *
     * @return
     */
    public SqlSession getSqlSession(String databaseName) {
        return this.getSqlSessionFactory(databaseName).openSession();
    }

    /*========== Properties ==========*/
    //protected Map<Class, Object> m_DaoMap = new HashMap<>();
    protected TConfig m_config;

    /*========== Constructor ==========*/
    public MybatisResource(TConfig config, Environment environment) {
        super(config, environment);
        m_config = config;
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


    /*========== Quick Query ==========*/
    public interface IQueryDelegator<TOut, TMapper> {
        TOut query(TMapper mapper);

    }

    protected <TMapper, TOut> TOut queryDb(Class<TMapper> mapperClz, IQueryDelegator queryDlgt) {
        TOut ret = null;
        try (SqlSession session = this.getSqlSessionFactory().openSession()) {
            TMapper mapper = session.getMapper(mapperClz);
            queryDlgt.query(mapper);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}
