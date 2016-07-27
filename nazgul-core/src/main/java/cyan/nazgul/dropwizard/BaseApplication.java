package cyan.nazgul.dropwizard;

import com.sun.org.apache.xerces.internal.impl.dv.xs.BooleanDV;
import com.sun.org.apache.xpath.internal.operations.Bool;
import cyan.nazgul.dropwizard.cli.DockerCommand;
import cyan.nazgul.dropwizard.component.DbHealthComponent;
import cyan.nazgul.dropwizard.component.FreemarkerComponent;
import cyan.nazgul.dropwizard.component.IComponent;
import cyan.nazgul.dropwizard.component.SwaggerComponent;
import cyan.nazgul.dropwizard.config.OneRingConfigSourceProvider;
import cyan.nazgul.dropwizard.resources.BaseResource;
import cyan.nazgul.dropwizard.resources.IResource;
import cyan.nazgul.dropwizard.resources.JdbiResource;
import cyan.nazgul.dropwizard.resources.MybatisResource;
import cyan.util.clazz.ClassUtil;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DreamInSun on 2016/7/21.
 */
public class BaseApplication<TConfig extends BaseConfiguration> extends Application<TConfig> {
    private static final Logger g_Logger = LoggerFactory.getLogger(BaseApplication.class);

    /*========== Static Properties ==========*/
    protected static String g_classRoot = null;

    /*========== Properties ==========*/
    protected List<IComponent<TConfig>> m_CompList = new ArrayList<>();
    protected String[] m_args = null;

    /*========== Configuration ==========*/
    protected Boolean g_isDebug = false;

    /*========== Constructor ==========*/
    protected BaseApplication(String[] args) {
        super();
        /* Save Run Arguments */
        this.m_args = args;
        g_classRoot = this.getClass().getPackage().getName();
        /* Init Compoments */
        m_CompList.add(new SwaggerComponent<>());
        m_CompList.add(new DbHealthComponent<>());
    }

    /*========== Application Initialization ==========*/
    @Override
    public void initialize(Bootstrap<TConfig> bootstrap) {
        System.out.println("\r\n/*========= Initializing ==========*/\r\n");

        /*===== Replace Configuration Provider =====*/
        bootstrap.setConfigurationSourceProvider(OneRingConfigSourceProvider.getInstance(g_isDebug));
        /* Enable variable substitution with environment variables */
//        bootstrap.setConfigurationSourceProvider(
//                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
//                        new EnvironmentVariableSubstitutor(false)
//                )
//        );
        /*===== Initialize Components =====*/
        for (IComponent comp : m_CompList) {
            comp.init(bootstrap);
        }
        /*===== CLI : Docker =====*/
        bootstrap.addCommand(new DockerCommand(this));
    }

    @Override
    public void run(TConfig config, Environment env) throws Exception {
        System.out.println("\r\n/*========= Run Application ==========*/\r\n");

        /*===== Config Swagger =====*/
        config.swaggerBundleConfiguration.setResourcePackage(this.g_classRoot + ".resources");

        /*===== Special Component =====*/
        //TODO Freemarker must be add here
        m_CompList.add(new FreemarkerComponent<>());

        /*===== Setup Components =====*/
        for (IComponent comp : m_CompList) {
            comp.run(config, env);
        }
        /*========= Scan Resource & Register ==========*/
        registerReources(g_classRoot + ".resources", config, env);
    }

    public void run() throws Exception {
        System.out.println("\n\r/*========== Start Application ==========*/\n\r");
        /*===== Determine Debug Mode =====*/
        List<String> argList = new ArrayList<>();
        for (String arg : this.m_args) {
            if (arg.equals("--debug")) {
                g_isDebug = true;
            } else {
                argList.add(arg);
            }
        }
        /* Remove --debug */
        if (g_isDebug) {
            String[] new_args = new String[m_args.length - 1];
            new_args = argList.toArray(new_args);
            this.m_args = new_args;
        }
        /*===== Kick Start =====*/
        this.run(this.m_args);
    }


    protected void registerReources(String resPath, TConfig config, Environment env) {
        g_Logger.info("\r\n/*========== Register Resources ===========*/\r\n" + resPath);

        List<Class<?>> resList = ClassUtil.getClassList(resPath, false, null);
        for (Class<?> resClazz : resList) {
            /*========== Create Resource Instance ==========*/
            Object resInstance = null;
            try {
                Class c = Class.forName(resClazz.getName());
                Class[] parameterTypes = {config.getClass(), Environment.class};
                java.lang.reflect.Constructor constructor = c.getConstructor(parameterTypes);
                Object[] parameters = {config, env};
                resInstance = constructor.newInstance(parameters);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            /*========== Register Resource ==========*/
//            Boolean res1 = resClazz.isAssignableFrom(IResource.class);
//            Boolean res2 = resClazz.isAssignableFrom(BaseResource.class);
//            Boolean res3 = resClazz.isAssignableFrom(JdbiResource.class);
//            Boolean res4 = resClazz.isAssignableFrom(MybatisResource.class);

            //if (resClazz.isAssignableFrom(IResource.class)) {
                env.jersey().register(resInstance);
            //}
        }
    }
}
