package cyan.nazgul.mngr;

import io.dropwizard.setup.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by DreamInSun on 2017/10/24.
 */
public class MyBatisMngr<TConfig> extends BaseMngr<TConfig> {
    /*========== Static Properties ==========*/
    protected static SqlSessionFactory g_sqlSessionFactory;

    public static void setSqlSessionFactory(SqlSessionFactory g_sessionFactory) {
        MyBatisMngr.g_sqlSessionFactory = g_sessionFactory;
    }

    protected SqlSessionFactory getSqlSessionFactory() {
        return MyBatisMngr.g_sqlSessionFactory;
    }

    /*========== Constructor ==========*/
    public MyBatisMngr(TConfig config, Environment env) {
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
}
