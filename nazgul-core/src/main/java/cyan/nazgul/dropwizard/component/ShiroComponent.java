package cyan.nazgul.dropwizard.component;


import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.secnod.dropwizard.shiro.ShiroBundle;
import org.secnod.dropwizard.shiro.ShiroConfiguration;
import org.secnod.shiro.jaxrs.ShiroExceptionMapper;


/**
 *
 * @see <a>https://github.com/silb/dropwizard-shiro</a>
 * Created by DreamInSun on 2017/12/16.
 */
@SuppressWarnings("unchecked")
public class ShiroComponent<TConfig extends BaseConfiguration> implements IComponent<TConfig> {

    /*========== Properties ==========*/
    private ShiroBundle<TConfig> m_shiro = new ShiroBundle<TConfig>() {
        @Override
        protected ShiroConfiguration narrow(TConfig configuration) {
            return configuration.getShiroConfig();
        }
    };

    /*==========  ==========*/
    @Override
    public void init(Bootstrap bootstrap) {
        bootstrap.addBundle(m_shiro);
    }

    @Override
    public void postInit(EnvConfig envConfig, Bootstrap bootstrap) {

    }

    @Override
    public void run(TConfig tConfig, Environment environment) {
        environment.jersey().register(new ShiroExceptionMapper());
    }
}
