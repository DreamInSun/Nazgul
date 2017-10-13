package cyan.nazgul.dropwizard;

import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.cli.DockerCommand;
import cyan.nazgul.dropwizard.component.*;
import cyan.nazgul.dropwizard.config.OneRingConfigSourceProvider;
import cyan.nazgul.dropwizard.resources.IResource;
import cyan.util.clazz.ClassUtil;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Nazgul BaseApplication
 * This Class is the full functioned Startup application. other Applcation can be Inherit from this one. Such as DbApplication extends a default jdbc database.
 * Created by DreamInSun on 2016/7/21.
 */
@SuppressWarnings ( { "unchecked", "rawtypes" } )
public class BaseApplication<TConfig extends BaseConfiguration> extends Application<TConfig> {
    private static final Logger g_logger = LoggerFactory.getLogger(BaseApplication.class);

    /*========== Static Properties ==========*/
    protected static String g_classRoot = null;

    /*========== Properties ==========*/
    protected List<IComponent<TConfig>> m_CompList = new ArrayList<>();
    protected List<IResource<TConfig>> m_resourceList = null;
    protected String[] m_args = null;
    Bootstrap<TConfig> m_bootstrap = null;

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
        m_CompList.add(new WebComponent<>());
        m_CompList.add(new MultipartyComponent<>());
        m_CompList.add(new SuperAdminComponent<>());
        m_CompList.add(new JobComponent<>(g_classRoot));
    }

    /*========== Application Initialization ==========*/
    @Override
    public void initialize(Bootstrap<TConfig> bootstrap) {
        System.out.println("\r\n/*================== Initializing ===================*/\r\n");
        m_bootstrap = bootstrap;
        /*===== Replace Configuration Provider =====*/
        bootstrap.setConfigurationSourceProvider(OneRingConfigSourceProvider.getInstance(g_isDebug, this.getClass()));
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

    public void postInitialize(EnvConfig envConfig, Bootstrap<TConfig> bootstrap) {
         /*===== Initialize Components =====*/
        for (IComponent comp : m_CompList) {
            comp.postInit(envConfig, bootstrap);
        }
    }

    @Override
    public void run(TConfig config, Environment env) throws Exception {
        g_logger.info("\r\n/*======================================================*/" +
                "\r\n/*================== Run Application ===================*/" +
                "\r\n/*======================================================*/\r\n");
        /*===== Update Project Config =====*/
        config.projectConfig.rootPackage = g_classRoot;
        /*===== Post Initialize =====*/
        this.postInitialize(EnvConfig.getRuntimeEnvConfig(), m_bootstrap);
        /*===== Special Component =====*/
        //TODO Freemarker must be add here
        m_CompList.add(new FreemarkerComponent<>());

        /*===== Setup Components =====*/
        for (IComponent comp : m_CompList) {
            comp.run(config, env);
        }
        /*========= Scan Resource & Register ==========*/
        m_resourceList = registerReources(g_classRoot + ".resources", config, env);
    }

    public void run() throws Exception {
        System.out.println("/*===========================================================*/");
        System.out.println("/*==================== Start Application ====================*/");
        System.out.println("/*===========================================================*/\r\n");

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
        /*===== Load Environment Config =====*/
        EnvConfig dockerEnv;
        /* Load EnvConfig */
        if (g_isDebug) {
            System.out.println("Get Docker EnvConfig via Developing Mode.");
            dockerEnv = EnvConfig.getFromResource("/config/docker-env.yml", this.getClass());
        } else {
            System.out.println("Get Docker EnvConfig via Standard Mode.");
            dockerEnv = EnvConfig.getFromEnvironment();
        }
        /* Print EnvConfig */
        if (dockerEnv != null) {
            System.out.println(dockerEnv.toString());
            System.out.println("\r\n/*========== Load OneRing Config ==========*/\r\n");
                /* Save Env for further usage */
            EnvConfig.setRuntimeEnvConfig(dockerEnv);
        }

        /*===== Kick Start =====*/
        this.run(this.m_args);
    }


    /*========== Assistant Function ==========*/

    /**
     * Search Resource class inhert from BaseResrouce, and register them.
     *
     * @param resPath resource class root package name
     * @param config applcation configuration
     * @param env applcation environment
     */
    protected List<IResource<TConfig>> registerReources(String resPath, TConfig config, Environment env) {
        g_logger.info("\r\n\r\n/*========== Register Resources ===========*/\r\n");

        List<Class<?>> resList = ClassUtil.getClassList(resPath, false, null);
        List<IResource<TConfig>> resourceList = new LinkedList<>();

        for (Class<?> resClazz : resList) {
            g_logger.info("Register Class: " + resClazz);
            /*========== Create Resource Instance ==========*/
            Object resInstance;
            try {
                /* Patch for debug file */
                if (resClazz.getName().endsWith("$1")) continue;
                Class c = Class.forName(resClazz.getName());
                Class[] parameterTypes = {config.getClass(), Environment.class};
                java.lang.reflect.Constructor constructor = c.getConstructor(parameterTypes);
                Object[] parameters = {config, env};
                resInstance = constructor.newInstance(parameters);
                env.jersey().register(resInstance);
                /* Add to Resource List */
                resourceList.add((IResource<TConfig>) resInstance);
            } catch (Exception e) {
                g_logger.error(e.getMessage());
            }
        }
        return resourceList;
    }

}
