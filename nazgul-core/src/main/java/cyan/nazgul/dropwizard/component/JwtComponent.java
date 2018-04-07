package cyan.nazgul.dropwizard.component;

import com.github.toastshaman.dropwizard.auth.jwt.JwtAuthFilter;
import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import cyan.nazgul.dropwizard.auth.JwtUser;
import cyan.nazgul.dropwizard.auth.JwtUserAuthenticator;
import cyan.nazgul.dropwizard.auth.JwtUserAuthorizer;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
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


    /*========== Constructor ==========*/
    public JwtComponent() {

    }

    /*========== Interface : IComponent ==========*/
    @Override
    public void init(Bootstrap<TConfig> bootstrap) {

    }

    @Override
    public void postInit(EnvConfig envConfig, Bootstrap<TConfig> bootstrap) {

    }

    @Override
    public void run(TConfig tConfig, Environment environment) {
        /*======  =====*/
        m_jwtKey = tConfig.getAuthConfig().convertJwtTokenSecret();
        /*======  =====*/
        final JwtConsumer consumer = new JwtConsumerBuilder()
                .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setRequireSubject() // the JWT must have a subject claim
                .setVerificationKey(new HmacKey(m_jwtKey)) // verify the signature with the public key
                .setRelaxVerificationKeyValidation() // relaxes key length requirement
                .build(); // create the JwtConsumer instance

        environment.jersey().register(new AuthDynamicFeature(
                new JwtAuthFilter.Builder<JwtUser>()
                        .setJwtConsumer(consumer)
                        .setRealm("realm")
                        .setPrefix("Bearer")
                        .setAuthenticator(new JwtUserAuthenticator())
                        .setAuthorizer(new JwtUserAuthorizer(tConfig))
                        .buildAuthFilter()));
        /*=====  =====*/
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(JwtUser.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
    }


}
