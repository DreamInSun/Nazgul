package cyan.svc.nazgulexample.mngr;

import cyan.nazgul.mngr.MybatisMngr;
import cyan.svc.nazgulexample.Configuration;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DreamInSun on 2017/10/20.
 */
public class AnotherMngr extends MybatisMngr<Configuration> {
    private final static Logger g_logger = LoggerFactory.getLogger(AnotherMngr.class);

    /*========== Constructor ==========*/
    public AnotherMngr(Configuration config, Environment env) {
        super(config, env);
        g_logger.info("Init RouteMngr");
    }

    /*========== Export Function ==========*/


    /*========== Assistant Function ==========*/


}
