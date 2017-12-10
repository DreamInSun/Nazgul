package cyan.svc.nazgul.example.mngr;

import cyan.nazgul.mngr.MyBatisMngr;
import cyan.svc.nazgul.example.Configuration;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DreamInSun on 2017/10/20.
 */
public class RouteMngr<TConfig extends Configuration> extends MyBatisMngr {
    private final static Logger g_logger = LoggerFactory.getLogger(RouteMngr.class);

    /*========== Constructor ==========*/
    public RouteMngr(TConfig config, Environment env) {
        super(config, env);
    }

    /*========== Export Function ==========*/


    /*========== Assistant Function ==========*/


}
