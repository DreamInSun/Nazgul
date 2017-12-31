package cyan.svc.nazgulexample.mngr;

import cyan.nazgul.mngr.MybatisMngr;
import cyan.svc.nazgulexample.Configuration;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DreamInSun on 2017/10/20.
 */
public class RouteMngr<TConfig extends Configuration> extends MybatisMngr<TConfig> {
    private final static Logger g_logger = LoggerFactory.getLogger(RouteMngr.class);

    /*========== Constructor ==========*/
    public RouteMngr(TConfig config, Environment env) {
        super(config, env);
        g_logger.info("Init RouteMngr");
    }

    /*========== Export Function ==========*/


    /*========== Assistant Function ==========*/


}
