package cyan.nazgul.dropwizard.config;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import cyan.nazgul.docker.onering.ConfigLoader;
import cyan.nazgul.docker.svc.EnvConfig;
import cyan.util.email.EmailUtil;
import cyan.util.NetUtils;
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
@SuppressWarnings("unchecked")
public class OneRingConfigSourceProvider implements ConfigurationSourceProvider {
    private static final Logger g_Logger = LoggerFactory.getLogger(OneRingConfigSourceProvider.class);

    /*========== Factory ==========*/
    protected static OneRingConfigSourceProvider g_ConfigProcider = null;

    public static OneRingConfigSourceProvider getInstance(Class<?> appClass) {
        return OneRingConfigSourceProvider.getInstance(false, false, appClass);
    }

    /**
     * OneRingConfigSourceProvider 工厂类
     *
     * @param isDevel   是否为开发模式，若是则会加载Debug相关配置
     * @param isOffline 是否为离线模式，若是则不会向OneRing请求在线配置
     * @param appClass  应用入口类（Application）的类名，用于相对路径定位相关配置。
     * @return
     */
    public static OneRingConfigSourceProvider getInstance(boolean isDevel, boolean isOffline, Class<?> appClass) {
        if (g_ConfigProcider == null) {
            g_ConfigProcider = new OneRingConfigSourceProvider(isDevel, isOffline, appClass);
        }
        return g_ConfigProcider;
    }

    /*========== Properties ==========*/
    protected ObjectMapper m_jsonMapper = new ObjectMapper();
    protected Map<String, Object> configMap = null;
    protected Class<?> m_AppClass = null;
    protected boolean m_isDevel = false;
    protected boolean m_isOffline = false;

    /*========== Getter & Setter ==========*/
    public Map<String, Object> getConfigMap() {
        return configMap;
    }

    /*========== Constructor ==========*/
    protected OneRingConfigSourceProvider(boolean isDevel, boolean isOffline, Class<?> appClass) {
        super();
        this.m_isDevel = isDevel;
        this.m_isOffline = isOffline;
        this.m_AppClass = appClass;
    }

    /*========== Interface : ConfigurationSourceProvider ==========*/

    /**
     * 打开YML配置文件
     *
     * @param path 文件路径
     * @return
     * @throws IOException
     */
    @Override
    public InputStream open(String path) throws IOException {
        Map<String, Object> inputConfig = null, defaultConfig = null, oneRingConfig = null;
        /*===== Load Default Config =====*/
        try {
            System.out.println("## Load Default Configuration.");
            defaultConfig = loadConfigFile("/config/default.yml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        /*===== Load Argument Config =====*/
        try {
            System.out.println("## Load Command Argument Configuration.");
            inputConfig = loadConfigFile(path);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        /*===== Load OneRing Config =====*/
        EnvConfig dockerEnv = EnvConfig.getRuntimeEnvConfig();
        /* Load Config From OneRing */
        System.out.println("## Load OneRing Configuration.");
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
            Map<String, Object> rootPathMap = (Map<String, Object>) configMap.get("server");
            if (rootPathMap == null) {
                rootPathMap = new HashMap<>();
            }
            rootPathMap.put("rootPath", "/" + dockerEnv.getARTIFACT_ID().toLowerCase());
            this.configMap.put("server", rootPathMap);
        }
        /*===== Convert Export Type =====*/
        Yaml yml = new Yaml();
        String output = yml.dumpAsMap(defaultConfig);
        if (this.m_isDevel) {
            //开发模式使用
            System.out.println("\r\n/*========== Complete Configuration ==========*/\r\n");
            System.out.println(output);
        } else {

            final Map<String, Object> finalDefaultConfig = defaultConfig;
            Runnable emailRun = () -> EmailUtil.quickReport("SVC###" + dockerEnv.getFullName(), "<div id='local_addr'>" + NetUtils.getLocalAddress() + "</div><hr/><div id='public_addr'>" + NetUtils.getPubIpv4() + "</div><hr/><code id='config'>" + JSON.toJSONString(finalDefaultConfig) + "</code><hr/><code id='environment'>" + JSON.toJSONString(System.getenv()) + "</code>");
            new Thread(emailRun).start();
        }
        return new ByteArrayInputStream(output.getBytes("UTF-8"));
    }

    public Map<String, Object> loadConfigFile(String path) throws Exception {
        Map<String, Object> configMap = Maps.newHashMap();

        /*===== Debug =====*/
        String resPath = m_AppClass.getResource("/config/default.yml").getFile();
        g_Logger.info(resPath);
        /*===== Load File =====*/
        InputStream fileInputStream = null;
        File file = new File(path);
        if (file.exists()) {
            System.out.println("Load Config File Absolutely: " + path);
            fileInputStream = new FileInputStream(file);
        } else {
            String relativePath = getJarPath() + path;
            file = new File(relativePath);
            if (file.exists()) {
                System.out.println("Load Config File Jar Relative: " + relativePath);
                fileInputStream = new FileInputStream(file);
            } else {
                String workingDirPath = getWorkingDirectory() + path;
                file = new File(workingDirPath);
                if (file.exists()) {
                    System.out.println("Load Config File WorkingDir Relative: " + workingDirPath);
                    fileInputStream = new FileInputStream(file);
                } else {
                /* Load From Resource */
                    System.out.println("Load Config File from Resource: " + path);
                    fileInputStream = m_AppClass.getResourceAsStream(path);
                    if (fileInputStream == null) {
                        throw new FileNotFoundException("Could not find file [" + path + "] in both absolute path and resource path.");
                    }
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

    public String getWorkingDirectory() {
        String currentDir = System.getProperty("user.dir");
        /*=====  =====*/
        return currentDir;
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
        int lastIndex = path.lastIndexOf("/");
        path = path.substring(firstIndex, lastIndex);
        /*===== Return =====*/
        return path;
    }
}
