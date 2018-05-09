package cyan.nazgul.dropwizard.component;

import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import cyan.nazgul.dropwizard.container.GlobalInstance;
import cyan.nazgul.dropwizard.resources.ErrorResource;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;

/**
 * 自定义附件注册组件
 * Created by DreamInSun on 2016/7/21.
 */
@SuppressWarnings("unchecked")
public class ErrorPageComponent<TConfig extends BaseConfiguration> implements IComponent<TConfig> {

    /*========== Constructor ==========*/
    public ErrorPageComponent() {

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
        String svcBase = "/" + GlobalInstance.getEnvConfig().getARTIFACT_ID().toLowerCase();
        // init Error pages
        final ErrorPageErrorHandler epeh = new ErrorPageErrorHandler();
        // 400 - Bad Request, leave alone
        epeh.addErrorPage(401, svcBase +"/error/general-error");
        epeh.addErrorPage(402,  svcBase +"/error/general-error");
        epeh.addErrorPage(403,  svcBase +"/error/403");
        epeh.addErrorPage(404,  svcBase +"/error/404");
        epeh.addErrorPage(405, 499,  svcBase +"/error/general-error");
        epeh.addErrorPage(500, 599,  svcBase +"/error/general-error");
        environment.getApplicationContext().setErrorHandler(epeh);
        environment.getAdminContext().setErrorHandler(epeh);

        ErrorResource errorResource = new ErrorResource(config, environment);
        environment.jersey().register(errorResource);
    }


}
