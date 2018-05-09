package cyan.nazgul.dropwizard.component;

import com.google.common.collect.Lists;
import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import cyan.nazgul.mngr.BaseMngr;
import cyan.nazgul.mngr.MngrFactory;
import cyan.util.clazz.ClassUtil;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Nazgul逻辑管理器 自动注册模块
 * Created by DreamInSun on 2018/4/7.
 */
public class MngrComponent<TConfig extends BaseConfiguration> implements IComponent<TConfig> {
    public static final Logger g_logger = LoggerFactory.getLogger(MngrComponent.class);

    /*========== Properties ==========*/
    private String m_classRoot;

    /*========== Constructor ==========*/
    public MngrComponent(String classRoot) {
        m_classRoot = classRoot;
    }

    @Override
    public void init(Bootstrap<TConfig> bootstrap) {

    }

    @Override
    public void postInit(EnvConfig envConfig, Bootstrap<TConfig> bootstrap) {

    }

    @Override
    public void run(TConfig tConfig, Environment environment) {
        /* 扫描Mngr路径 */
        List<Class<? extends BaseMngr>> mngrClz = scanMngr(m_classRoot);
        /* 初始化管理器并注册 */
        //TODO
        for (Class<? extends BaseMngr> clz : mngrClz) {
            MngrFactory.getMngr(clz, tConfig, environment);
        }

    }

        /*==========  ==========*/

    /**
     * Search Resource class inhert from BaseResrouce, and register them.
     *
     * @param resPath resource class root package name
     */
    protected List<Class<? extends BaseMngr>> scanMngr(String resPath) {
        g_logger.info("\r\n\r\n/*========== Register Manager ===========*/\r\n");
        String packagePath = resPath + ".mngr";
        // List<Class<?>> clzList = ClassUtil.getClassList(packagePath, true, cyan.nazgul.mngr.NazMngr.class);
        List<Class<?>> clzList = ClassUtil.getClassList(packagePath, true, null);

        List<Class<? extends BaseMngr>> mngrClzList = Lists.newLinkedList();

        for (Class<?> clz : clzList) {
            g_logger.info("Register Manager: " + clz);
            /*========== Create Resource Instance ==========*/
            try {
                /* Patch for debug file */
                if (clz.getName().endsWith("$1")) continue;
                /* Check base Class */
                if (clz.isAssignableFrom(BaseMngr.class)) {
                    mngrClzList.add((Class<? extends BaseMngr>) clz);
                }
            } catch (Exception e) {
                g_logger.error(e.getMessage());
            }
        }
        return mngrClzList;
    }
}
