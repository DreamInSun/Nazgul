package cyan.nazgul.dropwizard;

import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.auth.multiauth.MultiAuthDynamicFeature;
import cyan.nazgul.dropwizard.auth.multiauth.MultiAuthFilter;
import cyan.nazgul.dropwizard.auth.multiauth.MultiAuthValueFactoryProvider;
import cyan.nazgul.dropwizard.cli.DockerCommand;
import cyan.nazgul.dropwizard.cli.DockerCommandHandler;
import cyan.nazgul.dropwizard.component.*;
import cyan.nazgul.dropwizard.config.OneRingConfigSourceProvider;
import cyan.nazgul.dropwizard.container.GlobalInstance;
import cyan.nazgul.dropwizard.filter.CrossDomainFilter;
import cyan.nazgul.dropwizard.filter.NazProductLoggingFilter;
import cyan.nazgul.dropwizard.resources.IResource;
import cyan.svc.exception.NazExceptionMapper;
import cyan.util.clazz.ClassUtil;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import java.util.*;

/**
 * Nazgul BaseApplication
 * This Class is the full functioned Startup application. other Applcation can be Inherit from this one. Such as DbApplication extends a default jdbc database.
 * Created by DreamInSun on 2016/7/21.
 */
@SuppressWarnings({"unchecked", "rawtypes"})
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
    protected Boolean g_isOffline = false;

    /*========== Constructor ==========*/
    protected BaseApplication(String[] args) {
        super();
        /* Save Run Arguments */
        this.m_args = args;
        g_classRoot = this.getClass().getPackage().getName();
        /* Init Component */
        m_CompList.add(new SwaggerComponent<>(g_classRoot));

        m_CompList.add(new DbHealthComponent<>());
        /* Web */
        m_CompList.add(new WebComponent<>());
        m_CompList.add(new MultipartyComponent<>());
        m_CompList.add(new ErrorPageComponent());

        m_CompList.add(new MngrComponent<>(g_classRoot));
        m_CompList.add(new JobComponent<>(g_classRoot));

        m_CompList.add(new ShiroComponent<>());
        m_CompList.add(new JwtComponent<>(g_classRoot));
        m_CompList.add(new SuperAdminComponent<>(g_classRoot));

        m_CompList.add(new WebSocketComponent<>(g_classRoot));
    }

    /*========== Application Initialization ==========*/
    @Override
    public void initialize(Bootstrap<TConfig> bootstrap) {
        System.out.println("\r\n/*================== Initializing Dropwizard ===================*/\r\n");
        m_bootstrap = bootstrap;
        /*===== Replace Configuration Provider =====*/
        bootstrap.setConfigurationSourceProvider(OneRingConfigSourceProvider.getInstance(g_isDebug, g_isOffline, this.getClass()));
        /* Enable variable substitution with environment variables */
//        bootstrap.setConfigurationSourceProvider(
//                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
//                        new EnvironmentVariableSubstitutor(false)
//                )
//        );
        /*===== CLI : Docker =====*/
        bootstrap.addCommand(new DockerCommand(this, new DockerCommandHandler<BaseConfiguration>() {
            @Override
            public void processAfterConfigBeforeBootstrap(BaseConfiguration config) {
                /*===== Initialize Components =====*/
                g_logger.info("/*========== Initializing Components ==========*/");
                int compCnt = 1;
                Map<String, Boolean> nazComponents = config.getNazComponents();
                for (IComponent comp : m_CompList) {
                    String compName = comp.getClass().getSimpleName();
                    Boolean isEnable = nazComponents.get(compName);
                    g_logger.debug(String.format("Initialize Component %d/%d : %s", compCnt++, m_CompList.size(), compName));
                    if( isEnable == null ){
                        g_logger.debug(String.format("Not Configured"));
                    }else {
                        if (isEnable) {
                            comp.init(bootstrap);
                            g_logger.debug(String.format("Complete"));
                        } else {
                            g_logger.debug(String.format("Skipped"));
                        }
                    }
                }
            }
        }));

    }

    /**
     * 在Nazgul基础框架创建完毕后，执行Bundle的注册。
     *
     * @param envConfig
     * @param bootstrap
     */
    public void postInitialize(EnvConfig envConfig, Bootstrap<TConfig> bootstrap) {
        this.g_logger.info("/*========== Post Initializing Components ==========*/");
        int compCnt = 1;
        BaseConfiguration config = GlobalInstance.getConfiguration();
        Map<String, Boolean> nazComponents = config.getNazComponents();
        for (IComponent comp : m_CompList) {
            String compName = comp.getClass().getSimpleName();
            Boolean isEnable = nazComponents.get(compName);
            g_logger.info(String.format("Post Initialize Component %d/%d : %s", compCnt++, m_CompList.size(), compName));
            if( isEnable == null ){
                g_logger.debug(String.format("Not Configured"));
            }else {
                if (isEnable) {
                    comp.postInit(envConfig, bootstrap);
                    g_logger.debug(String.format("Complete"));
                } else {
                    g_logger.debug(String.format("Skipped"));
                }
            }
        }
    }

    @Override
    public void run(TConfig config, Environment env) throws Exception {
        g_logger.info("\r\n/*======================================================*/" +
                "\r\n/*================== Run Application ===================*/" +
                "\r\n/*======================================================*/\r\n");
        /*===== Update Project Config =====*/
        GlobalInstance.setConfiguration(config);
        config.getProjectConfig().setRootPackage(g_classRoot);
        /*===== Post Initialize =====*/
        this.postInitialize(EnvConfig.getRuntimeEnvConfig(), m_bootstrap);
        /*===== Special Component =====*/
        //TODO Freemarker must be add here
        m_CompList.add(new FreemarkerComponent<>());

        /*===== Setup Components =====*/
        this.g_logger.info("/*========== Setup Components ==========*/");
        int compCnt = 1;
        Map<String, Boolean> nazComponents = config.getNazComponents();
        for (IComponent comp : m_CompList) {
            String compName = comp.getClass().getSimpleName();
            Boolean isEnable = nazComponents.get(compName);
            g_logger.info(String.format("Setup Component %d/%d : %s", compCnt++, m_CompList.size(), compName));
            if( isEnable == null ){
                g_logger.debug(String.format("Not Configured"));
            }else {
                if (isEnable) {
                    comp.run(config, env);
                    g_logger.debug(String.format("Complete"));
                } else {
                    g_logger.debug(String.format("Skipped"));
                }
            }
        }
        /*===== Cross Domain =====*/
        if (config.getCrossdomainConfig().getEnable()) {
            env.getApplicationContext().addFilter(CrossDomainFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR));
        }
        /*===== Request Log =====*/
        //env.jersey().register(new NazLogFilter());
        env.jersey().register(new NazProductLoggingFilter());
        /*===== Exception Handler ======*/
        env.jersey().register(new NazExceptionMapper());
        /*===== Register Auth Strategy =====*/
        List<? extends MultiAuthFilter> authFilterList = GlobalInstance.getAuthFilterList();
        MultiAuthFilter[] simpleAuthFilters = new MultiAuthFilter[authFilterList.size()];
        authFilterList.toArray(simpleAuthFilters);
        env.jersey().register(new MultiAuthDynamicFeature(simpleAuthFilters));
        env.jersey().register(RolesAllowedDynamicFeature.class);

        /* Custom @Auth Annotation Inject Class */
        env.jersey().register(new MultiAuthValueFactoryProvider.Binder());
        /*  */

        /*========= Scan Resource & Register ==========*/
        m_resourceList = registerReources(g_classRoot + ".resources", config, env);
    }

    public void run() throws Exception {
        System.out.println("/*===========================================================*/");
        System.out.println("/*==================== Start Application ====================*/");
        System.out.println("/*===========================================================*/\r\n");

        /*===== Determine Debug Mode =====*/
        List<String> argList = new ArrayList<>();
        int specialArgCnt = 0;
        for (String arg : this.m_args) {
            if (arg.equals("--debug")) {
                g_isDebug = true;
                specialArgCnt++;
            } else if (arg.equals("--offline")) {
                g_isOffline = true;
                specialArgCnt++;
            } else {
                argList.add(arg);
            }
        }
        /* Remove Special Arguments */
        String[] new_args = new String[argList.size()];
        new_args = argList.toArray(new_args);
        this.m_args = new_args;

        /*===== Load Environment Config =====*/
        EnvConfig dockerEnv;
        //TODO add offline flag
        /* Load EnvConfig */
        if (g_isDebug) {
            System.out.println("Get Docker EnvConfig via Developing Mode.");
            dockerEnv = EnvConfig.getFromResource("/config/docker-env.yml", this.getClass());
        } else {
            System.out.println("Get Docker EnvConfig via Standard Mode.");
            dockerEnv = EnvConfig.getFromEnvironment();
        }
        /* Additional Status Flag */
        dockerEnv.setIsDebug(g_isDebug);
        dockerEnv.setIsOffline(g_isOffline);
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
     * @param config  applcation configuration
     * @param env     applcation environment
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
