package cyan.nazgul.mngr;

import cyan.nazgul.dropwizard.container.GlobalInstance;
import io.dropwizard.setup.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by DreamInSun on 2017/10/24.
 */
@SuppressWarnings("unchecked")
public class MybatisMngr<TConfig> extends BaseMngr<TConfig> {
    /*========== Static Properties ==========*/
    protected SqlSessionFactory getSqlSessionFactory() {
        return GlobalInstance.getSqlSessionFactory();
    }

    /*========== Constructor ==========*/
    public MybatisMngr(TConfig config, Environment env) {
        super(config, env);
    }


    /*==========  Assistant Function ==========*/
    protected Object executeSql(Class mapperClz, SqlHandler sqlHander) {
        Object ret = null;
        try {
            SqlSession session = this.getSqlSessionFactory().openSession();
            ret = sqlHander.execute(mapperClz.cast(session.getMapper(mapperClz)));
            session.commit();
            session.close();
        } catch (Exception e) {
            this.getLogger().error(e.getMessage());
        }
        return ret;
    }

    public interface SqlHandler<TMapper> {
        Object execute(TMapper mapper);
    }

    /**
     * 注意該方案需要自行調用session.close()
     * @return
     */
    public SqlSession getSqlSession(){
        return this.getSqlSessionFactory().openSession();
    }


    protected SqlSessionFactory getSqlSessionFactory(String databaseName) {
        return GlobalInstance.getSqlSessionFactoryMap().get(databaseName);
    }

    /**
     * 注意該方案需要自行調用session.close()
     * @return
     */
    public SqlSession getSqlSession(String databaseName ){
        return this.getSqlSessionFactory(databaseName).openSession();
    }
}
