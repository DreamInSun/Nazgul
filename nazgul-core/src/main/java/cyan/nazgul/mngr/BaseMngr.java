package cyan.nazgul.mngr;

import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base Manager for further extension
 * Created by DreamInSun on 2017/10/8.
 */
public class BaseMngr<TConfig> {

    /*========== Logger ==========*/
    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    /*========== Properties ==========*/
    protected TConfig m_config;
    protected Environment m_env;

    /*========== Factory ==========*/


    /*========== Constructor ==========*/
    protected BaseMngr(TConfig config, Environment env) {
        this.m_config = config;
        this.m_env = env;
        MngrFactory.registerMngr(this);
    }

    /*========== Assistant Function ==========*/
    protected Object getMngr(Class<? extends BaseMngr> clz) {
        return MngrFactory.getMngr(clz);
    }
}
