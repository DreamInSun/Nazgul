package cyan.nazgul.dropwizard.component;

import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import cyan.nazgul.dropwizard.auth.superadmin.SuperAdmin;
import cyan.nazgul.dropwizard.auth.superadmin.SuperAdminAuthBinder;
import cyan.nazgul.dropwizard.auth.superadmin.SuperAdminAuthenticator;
import cyan.nazgul.dropwizard.auth.superadmin.SuperAdminAuthorizer;
import cyan.nazgul.dropwizard.auth.multiauth.MultiCredentialAuthFilter;
import cyan.nazgul.dropwizard.container.GlobalInstance;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * 应用超管访问管理组件
 * Created by DreamInSun on 2016/7/21.
 */
@SuppressWarnings("unchecked")
public class SuperAdminComponent<TConfig extends BaseConfiguration> implements IComponent<TConfig> {

    /*========== Constructor ==========*/
    public SuperAdminComponent(String rootPackage) {

    }

    /*========== Interface : IComponent ==========*/
    @Override
    public void init(Bootstrap bootstrap) {

    }

    @Override
    public void postInit(EnvConfig envConfig, Bootstrap<TConfig> bootstrap) {

    }

    @Override
    public void run(TConfig config, Environment environment) {
        /*===== Authen =====*/
        SuperAdminAuthenticator superAdminAuthenticator = new SuperAdminAuthenticator(config);
        SuperAdminAuthorizer superAdminAuthorizer = new SuperAdminAuthorizer(config);
        /* Cache */
//        CachingAuthenticator<BasicCredentials, SuperAdmin> cachingAuthenticator = new CachingAuthenticator<>(
//                new MetricRegistry(), superAdminAuthenticator,
//                config.getAuthenticationCachePolicy());
//        environment.jersey().register(AuthFactory.binder(
//                new BasicAuthFactory<>(cachingAuthenticator,
//                        "Example Realm", AuthUser.class)));
        /* Chained Auth Strategy */
        MultiCredentialAuthFilter<SuperAdmin> basicCredentialAuthFilter = new MultiCredentialAuthFilter.Builder<SuperAdmin>()
                .setAuthenticator(superAdminAuthenticator)
                .setPrincipal(SuperAdmin.class)
                .setAuthorizer(superAdminAuthorizer)
                .setRealm("超级管理员")
                .setPrefix("basic")
                .buildAuthFilter();
        /* 添加全局授权验证链，在BaseApplication中注册所有授权链 */
        GlobalInstance.getAuthFilterList().add(basicCredentialAuthFilter);
        /* 注入@Auth注解支持 */
        GlobalInstance.getAuthBinderList().add(SuperAdminAuthBinder.class);
    }


}
