package cyan.nazgul.docker.onering;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cyan.nazgul.docker.svc.EnvConfig;
import io.github.xdiamond.client.XDiamondConfig;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DreamInSun on 2016/7/6.
 */
public class ConfigLoader {

    /*========== Static Properties ==========*/
    protected static ObjectMapper g_jsonMapper = new ObjectMapper();

    /*========== Properties ==========*/
    protected XDiamondConfig m_xDiamondConf = new XDiamondConfig();
    protected String configFilePath = null;

    /*========== Getter & Setter ==========*/
    public String getConfigFilePath() {
        return configFilePath;
    }

    /*========== Constructor ==========*/
    public ConfigLoader(EnvConfig envConfig) {
        if (envConfig == null) return;
        /*===== Init xDiamond Client =====*/
        m_xDiamondConf.setServerHost(envConfig.getCONFIG_CONN());
        m_xDiamondConf.setProfile(envConfig.getPROFILE());
        m_xDiamondConf.setVersion(envConfig.getSERVICE_VERSION());
        m_xDiamondConf.setGroupId(envConfig.getGROUP_ID());
        m_xDiamondConf.setArtifactId(envConfig.getARTIFACT_ID());
        m_xDiamondConf.setSecretKey(envConfig.getCONFIG_KEY());
        m_xDiamondConf.init();

        /*===== Save Path =====*/
        this.configFilePath = m_xDiamondConf.getConfigFilePath();
    }

    public File getConfigFile() throws FileNotFoundException {
        final File file = new File(configFilePath);

        /*===== Check Path Type =====*/
        if (!file.exists()) {
            throw new FileNotFoundException("File " + file + " not found");
        }
        return file;
    }

    public Map<String, Object> getConfigMap() throws IOException {
        Map<String, Object> retMap = null;
        /*===== Input protection =====*/
        if (this.configFilePath == null) {
            return retMap;
        }
        /*===== Convert File to JSON Map =====*/
        InputStream fileInputStream = new FileInputStream(new File(this.configFilePath));
        List<LinkedHashMap<String, Object>> result = g_jsonMapper.readValue(fileInputStream, new TypeReference<List<Map<String, Object>>>() {
        });
        retMap = new LinkedHashMap<>();
        /*===== Format Return Map =====*/
        for (Map<String, Object> configItem : result) {
            Map<String, Object> configValue = (Map<String, Object>) configItem.get("config");
            if (configValue != null) {
                String key = (String) configValue.get("key");
                String[] keyArr = key.split("\\.");
                Object val = configValue.get("value");
                Map<String, Object> curMap = retMap;

                for (int i = 0; i < keyArr.length; i++) {
                    String k = keyArr[i];
                    if (i == (keyArr.length - 1)) {
                        curMap.put(k, val);
                    } else {
                        Object obj = curMap.get(k);
                        if (obj instanceof Map<?, ?>) {
                            curMap = (Map<String, Object>) obj;
                            continue;
                        } else {
                            Map<String, Object> childObj = new LinkedHashMap<>();
                            curMap.put(k, childObj);
                            curMap = childObj;
                        }
                    }
                }
            }
        }
        /*===== Format Return Map =====*/
        return retMap;
    }
}
