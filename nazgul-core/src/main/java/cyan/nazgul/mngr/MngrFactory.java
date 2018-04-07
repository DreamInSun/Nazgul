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
@SuppressWarnings("unchecked")
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
    public static <TMngr extends BaseMngr> TMngr getMngr(Class<TMngr> clz) {
        Object retMngr = null;
        /*===== Get From Map =====*/
        if (g_MngrMap != null) {
            retMngr = g_MngrMap.get(clz.getName());
        }
        if (null == retMngr) {
            g_logger.error("程序执行过程中，" + clz.getName() + "尚未初始化和注册，请使用getMngr(Class<TMngr> clz, TConfig config, Environment env)");
        }
        return (TMngr) retMngr;
    }

    /**
     * 根据类型，获取全局逻辑管理器，若不存在则按照传入的Configuration和Enviroment进行初始化。<br/>
     * 注意! 定义Mngr的时候使用应用的Configuration而不是其他基类Configuration。<br/>
     * 例如：public class RouteMngr extends DbMngr<Configuration>
     * @param clz
     * @param config
     * @param env
     * @param <TMngr>
     * @param <TConfig>
     * @return
     */
    public static <TMngr extends BaseMngr, TConfig> TMngr getMngr(Class<TMngr> clz, TConfig config, Environment env) {
        Object retMngr = null;
        /*===== Get From Map =====*/
        if (g_MngrMap != null) {
            retMngr = g_MngrMap.get(clz.getName());
        }
        /*===== Create Mngr =====*/
        if (null == retMngr) {
            try {
                Class[] parameterTypes = {config.getClass(), Environment.class};
                java.lang.reflect.Constructor constructor = clz.getConstructor(parameterTypes);
                Object[] parameters = {config, env};
                retMngr = constructor.newInstance(parameters);
                MngrFactory.registerMngr(retMngr);
            } catch (Exception e) {
                g_logger.error(e.getMessage());
            }
        }
        /*=====  =====*/
        return (TMngr) retMngr;
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
