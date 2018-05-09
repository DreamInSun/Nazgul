package cyan.nazgul.dropwizard.component;

import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import cyan.util.clazz.ClassUtil;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.websockets.WebsocketBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * WebSocket组件
 * @see <a>https://github.com/LivePersonInc/dropwizard-websockets</a>
 * Created by DreamInSun on 2017/9/15.
 */
@SuppressWarnings("unchecked")
public class WebSocketComponent<TConfig extends BaseConfiguration> implements IComponent<TConfig> {

    /*========== Logger ==========*/
    private static final Logger g_Logger = LoggerFactory.getLogger(WebSocketComponent.class);

    /*========== Constructor ==========*/
    private String m_classRoot;
    private WebsocketBundle websocketBundle;

    /*========== Constructor ==========*/
    public WebSocketComponent(String classRoot) {
        m_classRoot = classRoot;
    }

    /*========== Interface : IComponent ==========*/
    @Override
    public void init(Bootstrap bootstrap) {
        Class[] wsServerClzArr = {};
        websocketBundle = new WebsocketBundle(wsServerClzArr);
        bootstrap.addBundle(websocketBundle);
    }

    @Override
    public void postInit(EnvConfig envConfig, Bootstrap<TConfig> bootstrap) {

    }

    @Override
    public void run(TConfig config, Environment environment) {
        this.registerWebsocketServer(m_classRoot + ".websocket");
    }

    /*==========  ==========*/

    protected void registerWebsocketServer(String resPath) {
        g_Logger.info("\r\n\r\n/*========== Register Websocket Server ===========*/\r\n");

        List<Class<?>> clzList = ClassUtil.getClassList(resPath, false, null);
        /*=====  =====*/
        for (Class<?> resClazz : clzList) {
            g_Logger.info("Register Websocket Server: " + resClazz);
            /*========== Create Resource Instance ==========*/
            Object resInstance;
            try {
                /* Patch for debug file */
                if (resClazz.getName().endsWith("$1")) continue;
                /* Create EndpointServerConfig　*/
                Class clz = Class.forName(resClazz.getName());
                //java.lang.reflect.Constructor constructor = clz.getConstructor();
                //resInstance = constructor.newInstance();
                websocketBundle.addEndpoint(clz);
            } catch (Exception e) {
                g_Logger.error(e.getMessage());
            }
        }
    }
}
