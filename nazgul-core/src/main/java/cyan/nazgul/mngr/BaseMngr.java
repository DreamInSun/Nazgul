package cyan.nazgul.mngr;

import io.dropwizard.setup.Environment;

/**
 * Base Manager for further extension
 * Created by DreamInSun on 2017/10/8.
 */
public class BaseMngr<TConfig> {

    /*========== Properties ==========*/
    protected TConfig m_config;
    protected Environment m_env;

    /*========== Constructor ==========*/
    public BaseMngr(TConfig config, Environment env) {
        this.m_config = config;
        this.m_env = env;
    }
}
