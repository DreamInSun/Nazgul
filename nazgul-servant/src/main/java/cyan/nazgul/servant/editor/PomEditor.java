package cyan.nazgul.servant.editor;

import cyan.nazgul.servant.entity.SvcConfig;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.Document;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DreamInSun on 2017/10/9.
 */
public class PomEditor {
    /*========== Properties ==========*/
    protected String m_filePath;

    /*========== Constructor ==========*/
    public PomEditor(String filePath) {
        this.m_filePath = filePath;
    }

    public Document loadFile() {

        Map<String, String> names = new HashMap<String, String>();
        names.put("pom", "http://maven.apache.org/POM/4.0.0");
        Document xmlDoc = null;
        try {
            SAXReader saxReader = new SAXReader();
            saxReader.getDocumentFactory().setXPathNamespaceURIs(names);
            xmlDoc = saxReader.read(new File(this.m_filePath));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return xmlDoc;
    }

    public void saveFile(Document pomDoc) {
        try {
            FileOutputStream out = new FileOutputStream(new File(m_filePath));
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = null;
            writer = new XMLWriter(out, format);
            writer.write(pomDoc);
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /*========== Export Function ==========*/
    public void saveFile(SvcConfig svcConfig) {
        Document pomDoc = loadFile();
        /* Get Nodes */
        Node nodeGroupId = pomDoc.selectSingleNode("/pom:project/pom:groupId");
        Node nodeArifactId = pomDoc.selectSingleNode("/pom:project/pom:artifactId");
        Node nodeVesion = pomDoc.selectSingleNode("/pom:project/pom:version");
        /* Set Node */
        if (svcConfig != null) {
            if (null != svcConfig.getSvc_name()) {
                String svcName = svcConfig.getSvc_name();
                String groupId = null;
                String artifactId = null;
                if (svcName != null) {
                    int pos_splitter = svcName.lastIndexOf(".");
                    if (pos_splitter > 0) {
                        groupId = svcName.substring(0, pos_splitter);
                        artifactId = svcName.substring(pos_splitter + 1);
                    }
                }
                nodeGroupId.setText(groupId);
                nodeArifactId.setText(artifactId);
            }
            if (null != svcConfig.getSvc_version()) {
                nodeVesion.setText(svcConfig.getApi_version());
            }
        }

        /*===== Save File =====*/
        saveFile(pomDoc);
    }

    /*========== Assistent Function ==========*/

}
