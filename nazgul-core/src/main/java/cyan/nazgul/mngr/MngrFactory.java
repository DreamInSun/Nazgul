package cyan.nazgul.mngr;

import com.google.common.collect.Maps;
import cyan.nazgul.dropwizard.container.GlobalInstance;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by DreamInSun on 2017/12/10.
 */
public class MngrFactory<TConfig extends Configuration> {
    private static final Logger g_logger = LoggerFactory.getLogger(MngrFactory.class);

    /*========== Static Properties ==========*/
    private static Map<String, Object> g_MngrMap = Maps.newHashMap();

    /*========== Export Function ==========*/
    public static void registerMngr(Object mngr) {
        if (mngr instanceof BaseMngr) {
            g_MngrMap.put(mngr.getClass().getName(), mngr);
        }
    }

    /*========== Static Function ==========*/
    public static Object getMngr(Class<? extends BaseMngr> clz) {
        Object retMngr = null;
        /*===== Get From Map =====*/
        if (g_MngrMap != null) {
            retMngr = g_MngrMap.get(clz.getName());
        }
        /*===== Create Mngr =====*/
        if (null == retMngr) {
            try {
                Class[] parameterTypes = {Configuration.class, Environment.class};
                java.lang.reflect.Constructor constructor = clz.getConstructor(parameterTypes);
                Object[] parameters = {GlobalInstance.getConfiguration(), GlobalInstance.getEnvConfig()};
                retMngr = constructor.newInstance(parameters);
                MngrFactory.registerMngr(retMngr);
            } catch (Exception e) {
                g_logger.error(e.getMessage());
            }
        }
        /*=====  =====*/
        return retMngr;
    }

    /*========== Factory ==========*/

    /*========== Constructor ==========*/

    /**
     * Should not use this constructor
     */
    private MngrFactory() {

    }

    /*==========  ==========*/

}
