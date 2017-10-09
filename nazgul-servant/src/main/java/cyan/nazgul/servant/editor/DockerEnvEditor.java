package cyan.nazgul.servant.editor;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.common.io.Resources;
import cyan.nazgul.servant.entity.SvcConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

/**
 * Created by DreamInSun on 2017/10/9.
 */
public class DockerEnvEditor {

    /*========== Properties ==========*/
    protected String m_filePath;

    /*========== Constructor ==========*/
    public DockerEnvEditor(String filePath) {
        this.m_filePath = filePath;
    }

    /*========== Export Function ==========*/
    public SvcConfig LoadFile() {
        SvcConfig svcConfig = null;
        try {
            /*===== Yml to Object =====*/
            BufferedInputStream bufferedInputStream = (BufferedInputStream) Resources.getResource(m_filePath).getContent();
            Map configMap = (Map<?, ?>) (new Yaml().load(bufferedInputStream));
            /*===== Deserialization =====*/
            ObjectMapper objMapper = new ObjectMapper();
            objMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); //禁止未知属性打断反序列化
            svcConfig = objMapper.readValue(JSONObject.toJSONString(configMap), SvcConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return svcConfig;
    }


    public void saveFile(SvcConfig svcConfig) {
        /*===== Protection =====*/
        svcConfig.setProfile("dev");
        /*===== Json to Yaml =====*/
        String jsonAsYaml = null;
        Map<String, Object> map = null;
        try {
            jsonAsYaml = new YAMLMapper().writeValueAsString(svcConfig);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        /*==== Save File ====*/
        try {
            File file = new File(m_filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            writer.write(jsonAsYaml);
            writer.flush();
            writer.close();
            //new Yaml().dump(jsonAsYaml, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createYaml(String yaml_url, String jsonString) throws JsonProcessingException, IOException {
        // parse JSON
        JsonNode jsonNodeTree = new ObjectMapper().readTree(jsonString);
        // save it as YAML
        String jsonAsYaml = new YAMLMapper().writeValueAsString(jsonNodeTree);

        Yaml yaml = new Yaml();
        Map<String, Object> map = (Map<String, Object>) yaml.load(jsonAsYaml);


    }


}
