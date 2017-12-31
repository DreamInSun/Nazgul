package cyan.nazgul.servant.editor;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import cyan.nazgul.servant.entity.DefaultConfig;
import cyan.nazgul.servant.entity.SvcConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Created by DreamInSun on 2017/12/25.
 */
public class DefaultConfigEditor {

    /*=========== Properties ===========*/
    protected String m_filePath;

    /*=========== Constructor ===========*/
    public DefaultConfigEditor(String filePath) {
        this.m_filePath = filePath;
    }

    /*=========== Export Function ===========*/

    /**
     * Change the default package path for resource scanning.
     * project:
     * rootPackage: cyan.nazgul.Weixin
     *
     * @param packageName
     */
    public void updatePackageName(String packageName) {

    }

    /*========== Assistant Function ==========*/
    private DefaultConfig loadDefaultConfig() {
        DefaultConfig defaultConfig = null;
        try {
            /*===== Yml to Object =====*/
            BufferedInputStream bufferedInputStream = (BufferedInputStream) Resources.getResource(m_filePath).getContent();
            Map configMap = (Map<?, ?>) (new Yaml().load(bufferedInputStream));
            /*===== Deserialization =====*/
            ObjectMapper objMapper = new ObjectMapper();
            objMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); //禁止未知属性打断反序列化
            defaultConfig = objMapper.readValue(JSONObject.toJSONString(configMap), DefaultConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defaultConfig;
    }
}
