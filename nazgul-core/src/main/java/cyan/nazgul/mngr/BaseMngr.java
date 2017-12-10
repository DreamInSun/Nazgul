package cyan.nazgul.mngr;

import com.google.common.collect.Maps;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Base Manager for further extension
 * Created by DreamInSun on 2017/10/8.
 */
public class BaseMngr<TConfig> {

    /*========== Logger ==========*/
    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    /*========== Manager Map ==========*/
    private static Map<String, Object> g_MngrMap = Maps.newHashMap();

    private void registerMngr(Object mngr) {
        if (mngr instanceof BaseMngr) {
            g_MngrMap.put(mngr.getClass().getName(), mngr);
        }
    }

    /**
     * full Class Name
     *
     * @param mngrName
     */
    public Object getMngr(String mngrName) {
        if (g_MngrMap != null) {
            return g_MngrMap.get(mngrName);
        }
        return null;
    }

    public Object getMngr(Class<? extends BaseMngr> clz) {
        if (g_MngrMap != null) {
            return g_MngrMap.get(clz.getName());
        }
        return null;
    }

    /*========== Properties ==========*/
    protected TConfig m_config;
    protected Environment m_env;

    /*========== Constructor ==========*/
    public BaseMngr(TConfig config, Environment env) {
        this.m_config = config;
        this.m_env = env;
        this.registerMngr(this);
    }
}
