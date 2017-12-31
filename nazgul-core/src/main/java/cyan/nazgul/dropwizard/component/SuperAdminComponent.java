package cyan.nazgul.dropwizard.component;

import com.google.common.collect.Lists;
import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import cyan.nazgul.dropwizard.auth.SuperAdmin;
import cyan.nazgul.dropwizard.auth.SuperAdminAuthenticator;
import cyan.nazgul.dropwizard.auth.SuperAdminAuthorizer;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthFilter;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.chained.ChainedAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import java.util.List;

/**
 * 应用超管访问管理组件
 * Created by DreamInSun on 2016/7/21.
 */
@SuppressWarnings("unchecked")
public class SuperAdminComponent<TConfig extends BaseConfiguration> implements IComponent<TConfig> {

    /*========== Constructor ==========*/
    public SuperAdminComponent() {

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
        AuthFilter basicCredentialAuthFilter = new BasicCredentialAuthFilter.Builder<SuperAdmin>()
                .setAuthenticator(superAdminAuthenticator)
                .setAuthorizer(superAdminAuthorizer)
                .setRealm("需要超级管理员权限")
                .setPrefix("basic")
                .buildAuthFilter();

        List<AuthFilter> filters = Lists.newArrayList(basicCredentialAuthFilter);
        /* Register Auth Strategy */
        environment.jersey().register(new AuthDynamicFeature(new ChainedAuthFilter(filters)));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        //If you want to use @Auth to inject a custom Principal type into your resource
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(SuperAdmin.class));
    }


}
