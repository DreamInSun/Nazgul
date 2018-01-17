package cyan.nazgul.servant.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import cyan.nazgul.servant.entity.FileMapping;
import cyan.nazgul.servant.entity.SvcConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

/**
 * Created by DreamInSun on 2017/10/8.
 */
public class FileUtil {

    /*========== FileMapping ==========*/

    public static FileMapping loadFileMapping() {
        FileMapping fileMapping = null;
        try {
            /*===== Yml to Object =====*/
            Map configMap;
            ObjectMapper mapper;
            try (InputStream inStream =  ClassLoader.getSystemResourceAsStream("setting/filemapping.yml")) {
                configMap = (Map<?, ?>) (new Yaml().load(inStream));
            }
            /*===== Deserialization =====*/
            mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); //禁止未知属性打断反序列化
            fileMapping = mapper.readValue(JSONObject.toJSONString(configMap), FileMapping.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileMapping;
    }

    public static FileMapping g_FileMapping;

    public static SvcConfig loadFromDockerfile(FileMapping fileMapping) {
        String dockerfilePaht = fileMapping.getDockerfile();
        File dockerfile = new File(dockerfilePaht);

        if (dockerfile.exists()) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dockerfilePaht)));
                String data = null;
                while ((data = br.readLine()) != null) {
                    System.out.println(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /*==========  ==========*/
    public String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "/n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    public static void contentCopy(File inFile, File outFile) throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader(inFile));
        BufferedWriter bufWriter = new BufferedWriter(new FileWriter(outFile));
        String line = bufReader.readLine();
        while (line != null) {
            bufWriter.write(line + "\n");
            line = bufReader.readLine();
        }
        bufWriter.flush();
        bufWriter.close();
        bufReader.close();
    }
}
