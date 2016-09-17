package cyan.nazgul.dropwizard.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cyan.nazgul.docker.onering.ConfigLoader;
import cyan.nazgul.docker.svc.EnvConfig;
import io.dropwizard.configuration.ConfigurationSourceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DreamInSun on 2016/7/19.
 */
public class OneRingConfigSourceProvider implements ConfigurationSourceProvider {
    private static final Logger g_Logger = LoggerFactory.getLogger(OneRingConfigSourceProvider.class);

    /*========== Factory ==========*/
    protected static OneRingConfigSourceProvider g_ConfigProcider = null;

    public static OneRingConfigSourceProvider getInstance(Class<?> appClass) {
        return OneRingConfigSourceProvider.getInstance(false, appClass);
    }

    public static OneRingConfigSourceProvider getInstance(boolean isDevel, Class<?> appClass) {
        if (g_ConfigProcider == null) {
            g_ConfigProcider = new OneRingConfigSourceProvider(isDevel, appClass);
        }
        return g_ConfigProcider;
    }

    /*========== Properties ==========*/
    protected ObjectMapper m_jsonMapper = new ObjectMapper();
    protected Map<String, Object> configMap = null;
    protected Class<?> m_AppClass = null;
    protected boolean m_isDevel = false;


    /*========== Getter & Setter ==========*/
    public Map<String, Object> getConfigMap() {
        return configMap;
    }

    /*========== Constructor ==========*/
    protected OneRingConfigSourceProvider(boolean isDevel, Class<?> appClass) {
        super();
        this.m_isDevel = isDevel;
        this.m_AppClass = appClass;
    }

    /*========== Interface : ConfigurationSourceProvider ==========*/

    /**
     * @param path
     * @return
     * @throws IOException
     */
    @Override
    public InputStream open(String path) throws IOException {
        Map<String, Object> inputConfig = null, defaultConfig = null, oneRingConfig = null;
        /*===== Load Default Config =====*/
        try {
            defaultConfig = loadConfigFile("/config/default.yml");
        } catch (Exception e) {
            g_Logger.error(e.getMessage());
        }
        /*===== Load Argument Config =====*/
        try {
            inputConfig = loadConfigFile(path);
        } catch (Exception e) {
            g_Logger.error(e.getMessage());
        }
        /*===== Load OneRing Config =====*/
        EnvConfig dockerEnv = EnvConfig.getRuntimeEnvConfig();
        /* Load Config From OneRing */
        oneRingConfig = new ConfigLoader(dockerEnv).getConfigMap();

        /*===== Merge Config =====*/
        if (inputConfig != null) {
            //defaultConfig.putAll(inputConfig);
            defaultConfig = this.mergeMap(defaultConfig, inputConfig);
        }

        if (oneRingConfig != null) {
            //defaultConfig.putAll(oneRingConfig);
            defaultConfig = this.mergeMap(defaultConfig, oneRingConfig);
        }
        /*===== Save ConfigMap =====*/
        this.configMap = defaultConfig;
        /*===== Modify ConfigMap =====*/
        if (dockerEnv != null) {
            Map<String, Object> rootPathMap = new HashMap<>();
            rootPathMap.put("rootPath", "/" + dockerEnv.getARTIFACT_ID().toLowerCase());
            this.configMap.put("server", rootPathMap);
        }
        /*===== Convert Export Type =====*/
        Yaml yml = new Yaml();
        String output = yml.dumpAsMap(defaultConfig);
        if (this.m_isDevel) {
            System.out.println("\r\n/*========== Complete Configuration ==========*/\r\n");
            System.out.println(output);//TODO Remove it
        }
        return new ByteArrayInputStream(output.getBytes("UTF-8"));
    }

    public Map<String, Object> loadConfigFile(String path) throws Exception {
        Map<String, Object> configMap = new HashMap<>();

        /*===== Debug =====*/
        String resPath = m_AppClass.getResource("/config/default.yml").getFile();
        g_Logger.info(resPath);
        /*===== Load File =====*/
        InputStream fileInputStream = null;
        File file = new File(path);
        if (file.exists()) {
            System.out.println("Load File Absolutely: " + path);
            fileInputStream = new FileInputStream(file);
        } else {
            String relativePath = getJarPath() + path;
            file = new File(relativePath);
            if (file.exists()) {
                System.out.println("Load File Relative: " + relativePath);
                fileInputStream = new FileInputStream(file);
            } else {
                /* Load From Resource */
                System.out.println("Load File Resource: " + path);
                fileInputStream = m_AppClass.getResourceAsStream(path);
                if (fileInputStream == null) {
                    throw new FileNotFoundException("Could not find file [" + path + "] in both absolute path and resource path.");
                }
            }
        }
        /*===== Parse Config File =====*/
        if (path.endsWith(".yml")) {
            Yaml yml = new Yaml();
            configMap = (Map<String, Object>) (yml.load(fileInputStream));
        } else if (path.endsWith(".json")) {
            List<LinkedHashMap<String, Object>> result = m_jsonMapper.readValue(fileInputStream, new TypeReference<List<Map<String, Object>>>() {
            });
            g_Logger.debug(result.toString());
        } else {
            throw new Exception("Unsupport Confil File Format");
        }

        return configMap;
    }


    public Map<String, Object> mergeMap(Map<String, Object> destMap, Map<String, Object> srcMap) {
        for (Map.Entry<String, Object> entry : srcMap.entrySet()) {
            String key = entry.getKey();
            Object srcVal = entry.getValue();
            Object destVal = destMap.get(key);
            if (destVal instanceof Map) {
                if (srcVal instanceof Map) {
                    this.mergeMap((Map<String, Object>) destVal, (Map<String, Object>) srcVal);
                }
            } else if (destVal == null) {
                destMap.put(key, srcVal);
            } else {
                destMap.put(key, srcVal);
            }
        }
        return destMap;
    }


    public String getJarPath() {
        /*===== Get Jar File Path =====*/
        String path = this.m_AppClass.getProtectionDomain().getCodeSource().getLocation().getPath();
        try {
            path = java.net.URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /*===== Get Jar Directory Path =====*/
        int firstIndex = path.indexOf("/") + 1;
        int lastIndex = path.lastIndexOf("/") + 1;
        path = path.substring(firstIndex, lastIndex);
        /*===== Return =====*/
        return path;
    }

}
