package cyan.svc.nazgulexample.mngr;

import cyan.nazgul.mngr.MngrFactory;
import cyan.nazgul.mngr.MybatisMngr;
import cyan.svc.nazgulexample.Configuration;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DreamInSun on 2017/10/20.
 */
public class RouteMngr extends MybatisMngr<Configuration> {
    private final static Logger g_logger = LoggerFactory.getLogger(RouteMngr.class);
    /*========== Properties ==========*/
    AnotherMngr m_anotherMngr;

    /*========== Constructor ==========*/
    public RouteMngr(Configuration config, Environment env) {
        super(config, env);
        m_anotherMngr = MngrFactory.getMngr(AnotherMngr.class, config, env);
        g_logger.info("Init RouteMngr");
    }

    /*========== Export Function ==========*/


    /*========== Assistant Function ==========*/


}
