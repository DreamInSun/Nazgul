package cyan.nazgul.mngr;

import cyan.nazgul.dropwizard.DbConfiguration;
import cyan.nazgul.dropwizard.container.GlobalInstance;
import io.dropwizard.setup.Environment;
import org.apache.ibatis.session.SqlSession;

import javax.persistence.EntityManager;

/**
 * Created by DreamInSun on 2017/10/24.
 */
public class DbMngr<TConfig extends DbConfiguration> extends MybatisMngr<TConfig> {
    /*========== Static Properties ==========*/
    public static EntityManager getEntityManager() {
        return GlobalInstance.getEntityManager();
    }

    /*========== Constructor ==========*/
    public DbMngr(TConfig config, Environment env) {
        super(config, env);
    }


    /*==========  Assistant Function ==========*/
}
