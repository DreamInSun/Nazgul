package cyan.nazgul.docker.svc;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by DreamInSun on 2016/7/4.
 */
public class EnvConfig {


    /*========== Static Properties ==========*/
    protected static EnvConfig runtimeEnvConfig = null;

    /*========== Static Getter & Setter ==========*/
    public static EnvConfig getRuntimeEnvConfig() {
        return runtimeEnvConfig;
    }

    public static void setRuntimeEnvConfig(EnvConfig runtimeEnvConfig) {
        EnvConfig.runtimeEnvConfig = runtimeEnvConfig;
    }

    /*========== Properties ==========*/
    @JsonProperty
    protected String CONFIG_CONN;
    @JsonProperty
    protected String SERVICE_NAME;
    @JsonProperty
    protected String SERVICE_VERSION;
    @JsonProperty
    protected String API_VERSION;
    @JsonProperty
    protected String PROFILE;
    @JsonProperty
    protected String CONFIG_KEY;

    protected String GROUP_ID;
    protected String ARTIFACT_ID;

    /*========== Factory ==========*/
    public static EnvConfig getFromEnvironment() {
        EnvConfig envConf = new EnvConfig();

        Field[] fields = envConf.getClass().getDeclaredFields();

        try {
            for (Field key : fields) {
                String name = key.getName();
                String val = System.getenv(name);
                if (val != null) {
                    Method method = envConf.getClass().getMethod("set" + name, String.class);
                    method.invoke(envConf, val);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return envConf;
    }

    public static EnvConfig getDefault() throws Exception {
        EnvConfig envConf = new EnvConfig();
        envConf.setCONFIG_CONN("config.17orange.com");
        envConf.setPROFILE("Product");
        envConf.setSERVICE_VERSION("LTS");
        envConf.setCONFIG_KEY("123456");
        envConf.setSERVICE_NAME("orange.demo.DockerCI");
        return envConf;
    }

    public static EnvConfig getFromResource(String resourcePath, Class<?> appClass) throws FileNotFoundException {
        EnvConfig envConf = new EnvConfig();
        envConf.loadFromResource(resourcePath, appClass);
        return envConf;
    }

    /*========== Constructor ==========*/
    public EnvConfig() {

    }

    public void loadFromResource(String path , Class<?> appClass) throws FileNotFoundException {

        String resPath = appClass.getResource(path).getPath();


        InputStream inputStream = appClass.getResourceAsStream(path);
        if (inputStream != null) {
            this.loadFromYmal(inputStream);
        } else {
            new FileNotFoundException("Reousrce path [" + path + "] not found.");
        }

    }

    public void loadFromYmal(InputStream ymlInputStream) {

        /*===== Parse YAML =====*/
        Yaml yml = new Yaml();
        Map configMap = (Map<?, ?>) (yml.load(ymlInputStream));
        /*===== Fill Properties =====*/
        try {
            this.setCONFIG_CONN(String.valueOf(configMap.get("CONFIG_CONN")));
            this.setCONFIG_KEY(String.valueOf(configMap.get("CONFIG_KEY")));
            this.setSERVICE_NAME(String.valueOf(configMap.get("SERVICE_NAME")));
            this.setSERVICE_VERSION(String.valueOf(configMap.get("SERVICE_VERSION")));
            this.setPROFILE(String.valueOf(configMap.get("PROFILE")));
            this.setAPI_VERSION(String.valueOf(configMap.get("API_VERSION")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*========== Getter & Setter ==========*/
    public String getCONFIG_CONN() {
        return CONFIG_CONN;
    }

    public void setCONFIG_CONN(String CONFIG_CONN) {
        this.CONFIG_CONN = CONFIG_CONN;
    }

    public String getSERVICE_NAME() {
        return SERVICE_NAME;
    }

    public void setSERVICE_NAME(String SERVICE_NAME) throws Exception {
        this.SERVICE_NAME = SERVICE_NAME;
        if (this.SERVICE_NAME != null) {
            String[] svcNameSegments = SERVICE_NAME.split("(?=[A-Z])");
            int pos_splitter = SERVICE_NAME.lastIndexOf(".");
            if (pos_splitter > 0) {
                this.GROUP_ID = SERVICE_NAME.substring(0, pos_splitter);
                this.ARTIFACT_ID = SERVICE_NAME.substring(pos_splitter + 1);
            } else {
                throw new Exception("SERVICE_NAME format Error.");
            }
        }

    }

    public String getGROUP_ID() {
        return GROUP_ID;
    }

    public String getARTIFACT_ID() {
        return ARTIFACT_ID;
    }

    public String getPROFILE() {
        return PROFILE;
    }

    public void setPROFILE(String PROFILE) {
        this.PROFILE = PROFILE;
    }

    public String getSERVICE_VERSION() {
        return SERVICE_VERSION;
    }

    public void setSERVICE_VERSION(String SERVICE_VERSION) {
        this.SERVICE_VERSION = SERVICE_VERSION;
    }

    public String getCONFIG_KEY() {
        return CONFIG_KEY;
    }

    public void setCONFIG_KEY(String CONFIG_KEY) {
        this.CONFIG_KEY = CONFIG_KEY;
    }

    public String getAPI_VERSION() {
        return API_VERSION;
    }

    public void setAPI_VERSION(String API_VERSION) {
        this.API_VERSION = API_VERSION;
    }

    /*==========  ==========*/


    /*========== toString ==========*/

    @Override
    public String toString() {
        return new Yaml().dumpAsMap(this);
    }
}
