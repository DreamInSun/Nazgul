package cyan.nazgul.mngr;

import io.dropwizard.setup.Environment;

/**
 * Manager for Mongo Connections
 * Created by DreamInSun on 2017/10/8.
 */
public class MongoMngr<TConfig> extends BaseMngr<TConfig> {

    /*========== Constructor ==========*/
    public MongoMngr(TConfig config, Environment env) {
        super(config, env);
    }
}
