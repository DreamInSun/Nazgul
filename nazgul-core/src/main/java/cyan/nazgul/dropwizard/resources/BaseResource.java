package cyan.nazgul.dropwizard.resources;


import com.sun.org.apache.regexp.internal.RE;
import cyan.nazgul.docker.svc.EnvConfig;
import cyan.util.ResourceUtil;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.util.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by DreamInSun on 2016/7/27.
 */
public class BaseResource<TConfig extends Configuration> implements IResource<TConfig> {
    @SuppressWarnings("Unused")
    private static final Logger g_logger = LoggerFactory.getLogger(BaseResource.class);

    /*========== Properties ==========*/
    protected Environment enviroment;
    public TConfig config;

    /*========== Getter & Setter ==========*/
    public Environment getEnviroment() {
        return enviroment;
    }

    @Override
    public TConfig getConfig() {
        return null;
    }

    /*========== Constructor ==========*/
    public BaseResource(TConfig config, Environment env) {
        this.config = config;
        this.enviroment = env;
        /* Setup Base Variables */
        ResourceUtil.setG_namespace(EnvConfig.getRuntimeEnvConfig().getSERVICE_NAME());
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
}
