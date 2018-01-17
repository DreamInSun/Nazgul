package cyan.nazgul.servant.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import cyan.nazgul.servant.entity.SvcConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * YML 文件处理工具类
 * Created by DreamInSun on 2018/1/17.
 */
public class YmlUtil {

    public static Map<?, ?> LoadFile(String filePath) {
        SvcConfig svcConfig = null;
        Map configMap = null;
        try {
            /*===== Yml to Object =====*/

            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(filePath)));
            configMap = (Map<?, ?>) (new Yaml().load(bufferedInputStream));
            /*===== Deserialization =====*/
//            ObjectMapper objMapper = new ObjectMapper();
//            objMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); //禁止未知属性打断反序列化
//            svcConfig = objMapper.readValue(JSONObject.toJSONString(configMap), SvcConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configMap;
    }
}
