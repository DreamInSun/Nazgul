package cyan.nazgul.dropwizard.resources;


import cyan.nazgul.docker.svc.EnvConfig;
import cyan.util.ResourceUtil;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.InputStream;

/**
 * Created by DreamInSun on 2016/7/27.
 */
public class BaseResource<TConfig extends Configuration> implements IResource<TConfig> {
//    @SuppressWarnings("Unused")
//    private static final Logger g_logger = LoggerFactory.getLogger(BaseResource.class);

    /*========== Logger ==========*/
    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    /*========== Properties ==========*/
    protected Environment enviroment;
    public TConfig config;

    /*========== Getter & Setter ==========*/
    public Environment getEnviroment() {
        return enviroment;
    }

    @Override
    public TConfig getConfig() {
        return this.config;
    }


    /*========== Constructor ==========*/
    public BaseResource(TConfig config, Environment env) {
        this.config = config;
        this.enviroment = env;
        /* Setup Base Variables */
        ResourceUtil.setG_namespace(EnvConfig.getRuntimeEnvConfig().getSERVICE_NAME());
    }

    @Override
    public int initialize(TConfig config, Environment env) {
        this.getLogger().info("Initializing " + this.getClass().getName());
        return 0;
    }

    public File getResource(String path) {
        File file = ResourceUtil.loadFile(path);
        return file;
    }

    public InputStream getResourceAsStream(String path) {
        return ResourceUtil.loadResourceAsStream(path);
    }
    /*
    public String getResourcePath(String path) {
        String ret = null;
        try {
            URL url = this.getClass().getResource(path);
            Resource res = enviroment.getApplicationContext().getResource(path);
            ret = res.getFile().getPath();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
    //*/

    /*========= Util : Redirect ==========*/
    public Response redirect(Response.Status status, String lcoation) {

        return Response.status(status)
                .header("Content-Type", MediaType.TEXT_HTML)
                .header("Location", "/" + EnvConfig.getRuntimeEnvConfig().getARTIFACT_ID().toLowerCase() + lcoation)
                .build();
    }

    public Response redirect301(String lcoation) {
        return this.redirect(Response.Status.MOVED_PERMANENTLY, lcoation);
    }

    public Response redirect302(String lcoation) {
        return this.redirect(Response.Status.FOUND, lcoation);
    }

    public Response redirect303(String lcoation) {
        return this.redirect(Response.Status.SEE_OTHER, lcoation);
    }

    public Response redirect305(String lcoation) {
        return this.redirect(Response.Status.USE_PROXY, lcoation);
    }

    public Response redirect307(String lcoation) {
        return this.redirect(Response.Status.TEMPORARY_REDIRECT, lcoation);
    }

}
