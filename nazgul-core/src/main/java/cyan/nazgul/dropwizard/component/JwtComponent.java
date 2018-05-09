package cyan.nazgul.dropwizard.component;


import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import cyan.nazgul.dropwizard.auth.jwt.*;
import cyan.nazgul.dropwizard.config.AuthConfig;
import cyan.nazgul.dropwizard.container.GlobalInstance;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DreamInSun on 2018/3/27.
 */
public class JwtComponent<TConfig extends BaseConfiguration> implements IComponent<TConfig> {
    public static final Logger g_logger = LoggerFactory.getLogger(JwtComponent.class);

    /*========== Properties ==========*/
    private byte[] m_jwtKey;
    private AuthConfig m_authConfig;


    /*========== Constructor ==========*/
    public JwtComponent(String rootPackage) {

    }

    /*========== Interface : IComponent ==========*/
    @Override
    public void init(Bootstrap<TConfig> bootstrap) {

    }

    @Override
    public void postInit(EnvConfig envConfig, Bootstrap<TConfig> bootstrap) {

    }

    @Override
    public void run(TConfig config, Environment environment) {

        /*======  =====*/
        m_authConfig = config.getAuthConfig();
        m_jwtKey = config.getAuthConfig().convertJwtTokenSecret();
        /*======  =====*/
        final JwtConsumer consumer = new JwtConsumerBuilder()
                .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setRequireSubject() // the JWT must have a subject claim
                .setVerificationKey(new HmacKey(m_jwtKey)) // verify the signature with the public key
                .setRelaxVerificationKeyValidation() // relaxes key length requirement
                .build(); // create the JwtConsumer instance
        /*=====  =====*/
        JwtAuthFilter<JwtUser> jwtAuthFilter = new JwtAuthFilter.Builder<JwtUser>()
                .setJwtConsumer(consumer)
                .setRealm(m_authConfig.getJwtRealm())
                .setPrefix(m_authConfig.getJwtPreFix())
                .setAuthenticator(new JwtUserAuthenticator())
                .setAuthorizer(new JwtUserAuthorizer(config))
                .setPrincipal(JwtUser.class)
                .buildAuthFilter();
         /* 添加全局授权验证链，在BaseApplication中注册所有授权链 */
        GlobalInstance.getAuthFilterList().add(jwtAuthFilter);
        /* 注入@Auth注解支持 */
        GlobalInstance.getAuthBinderList().add(JwtAuthBinder.class);

    }


}
