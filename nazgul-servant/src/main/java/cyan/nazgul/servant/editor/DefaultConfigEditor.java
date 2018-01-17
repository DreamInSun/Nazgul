package cyan.nazgul.servant.editor;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import cyan.nazgul.servant.entity.DefaultConfig;
import cyan.nazgul.servant.entity.ProjectConfig;
import cyan.nazgul.servant.entity.SvcConfig;
import cyan.nazgul.servant.util.FileUtil;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
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

    public void saveFile(SvcConfig m_SvcConfig) {
        String packageName = m_SvcConfig.getSvc_name().replace('-', '.').toLowerCase();
        updateBasePackageName(packageName);
    }

    /*========== Assistant Function ==========*/
    private DefaultConfig loadDefaultConfig() {
        DefaultConfig defaultConfig = null;
        try {
            /*===== Yml to Object =====*/
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(this.m_filePath)));
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

    public ProjectConfig getProjectConfig() {
        return this.loadDefaultConfig().getProject();
    }

    /**
     * 逐行扫描Default.yml文件，将root package替换为新的。
     *
     * @param newPackageName
     */
    public void updateBasePackageName(String newPackageName) {
        String oldPackageName = this.getProjectConfig().getRootPackage();
        /* Replace */
        try {
              /*==== Prepare =====*/
            File inFile = new File(m_filePath);
            File outFile = new File(m_filePath + ".out");
            BufferedReader bufReader = new BufferedReader(new FileReader(inFile));
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter(outFile));
            /*===== Scan && Replace =====*/
            String line = bufReader.readLine();
            while (line != null) {
                if (line.contains(oldPackageName)) {
                    line = line.replace(oldPackageName, newPackageName);
                }
                /*=====  =====*/
                bufWriter.write(line + "\n");
                line = bufReader.readLine();
            }
            /*===== Finish Write =====*/
            bufWriter.flush();
            bufWriter.close();
            bufReader.close();
            /*===== Rename Output File =====*/
            FileUtil.contentCopy(outFile, inFile);
            outFile.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
