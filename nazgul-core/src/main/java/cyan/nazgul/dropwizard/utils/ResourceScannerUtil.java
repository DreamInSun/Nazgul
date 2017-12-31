package cyan.nazgul.dropwizard.utils;

import cyan.nazgul.dropwizard.resources.IResource;
import cyan.util.clazz.ClassUtil;
import io.dropwizard.setup.Environment;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by DreamInSun on 2017/12/27.
 */
public class ResourceScannerUtil {

    /**
     * Search Resource class inhert from BaseResrouce, and register them.
     *
     * @param resPath resource class root package name
     * @param config  applcation configuration
     * @param env     applcation environment
     */
//    protected List<IResource<TConfig>> registerEntity<TBase>(String resPath, TConfig config, Environment env) {
//        g_logger.info("\r\n\r\n/*========== Register Resources ===========*/\r\n");
//
//        List<Class<?>> resList = ClassUtil.getClassList(resPath, false, null);
//        List<IResource<TConfig>> resourceList = new LinkedList<>();
//
//        for (Class<?> resClazz : resList) {
//            g_logger.info("Register Class: " + resClazz);
//            /*========== Create Resource Instance ==========*/
//            Object resInstance;
//            try {
//                /* Patch for debug file */
//                if (resClazz.getName().endsWith("$1")) continue;
//                /* */
//                Class c = Class.forName(resClazz.getName());
//                Class[] parameterTypes = {config.getClass(), Environment.class};
//                java.lang.reflect.Constructor constructor = c.getConstructor(parameterTypes);
//                Object[] parameters = {config, env};
//                resInstance = constructor.newInstance(parameters);
//                env.jersey().register(resInstance);
//                /* Add to Resource List */
//                resourceList.add((IResource<TConfig>) resInstance);
//            } catch (Exception e) {
//                g_logger.error(e.getMessage());
//            }
//        }
//        return resourceList;
//    }
}
