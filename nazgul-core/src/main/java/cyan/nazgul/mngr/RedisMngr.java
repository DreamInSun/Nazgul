package cyan.nazgul.mngr;

import io.dropwizard.setup.Environment;

/**
 * Manager for redis connections
 * Created by DreamInSun on 2017/10/8.
 */
public class RedisMngr<TConfig> extends BaseMngr<TConfig>{

    /*========== Constructor ==========*/
    public RedisMngr(TConfig config, Environment env) {
        super(config, env);
    }
}
