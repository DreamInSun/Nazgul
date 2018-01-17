package cyan.nazgul.servant.onering;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cyan.nazgul.servant.entity.FileMapping;
import cyan.nazgul.servant.onering.entity.ConfigItem;
import cyan.nazgul.servant.util.YmlUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DreamInSun on 2018/1/17.
 */
public class ConfigConvertor {

    /*========== Properties ==========*/
    protected String m_develConfigFilePath;
    protected String m_productConfigFilePath;

    /*========== Constructor ==========*/
    public ConfigConvertor(FileMapping fileMapping) {
        m_develConfigFilePath = fileMapping.getDevelConf();
        m_productConfigFilePath = fileMapping.getProductConf();
    }

    /*========== Export Function ==========*/
    public String getDevelConfigJson() {
        Map<?, ?> configMap = YmlUtil.LoadFile(m_develConfigFilePath);
        String retStr = convertMapToConfigItemArr(configMap);
        return retStr;
    }

    public String getProductConfigJson() {
        Map<?, ?> configMap = YmlUtil.LoadFile(m_productConfigFilePath);
        String retStr = convertMapToConfigItemArr(configMap);
        return retStr;
    }

    public String convertMapToConfigItemArr(Map<?, ?> configMap) {
        Map<String, Object> flatMap = flattenMap(configMap);
        List<ConfigItem> configItemList = Lists.newArrayList();
        for (Map.Entry entry : flatMap.entrySet()) {
            ConfigItem item = new ConfigItem();
            Object value = entry.getValue();
            item.setKey((String) entry.getKey());
            if (value instanceof String) {
                item.setValue((String) value);
                item.setDescription("type:String");
            } else if (value instanceof Integer) {
                item.setValue(Integer.toString((Integer) value));
                item.setDescription("type:Integer");
            } else if (value instanceof Float) {
                item.setValue(Float.toString((Float) value));
                item.setDescription("type:Float");
            } else if (value instanceof Boolean) {
                item.setValue(Boolean.toString((Boolean) value));
                item.setDescription("type:Boolean");
            }
            configItemList.add(item);
        }
        return JSONObject.toJSONString(configItemList);
    }

    private Map<String, Object> flattenMap(Map<?, ?> configMap) {
        Map<String, Object> retMap = Maps.newLinkedHashMap();
        for (Map.Entry entry : configMap.entrySet()) {
            String key = (String) entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                Map<String, Object> childMap = flattenMap((Map<?, ?>) value);
                for (Map.Entry childEntry : childMap.entrySet()) {
                    retMap.put(key + "." + childEntry.getKey(), childEntry.getValue());
                }
            } else {
                retMap.put(key, value);
            }
        }
        return retMap;
    }
}
