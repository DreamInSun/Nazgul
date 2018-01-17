package cyan.nazgul.servant.validator;

import cyan.nazgul.servant.editor.DefaultConfigEditor;
import cyan.nazgul.servant.editor.PomEditor;
import cyan.nazgul.servant.entity.FileMapping;
import cyan.nazgul.servant.entity.SvcConfig;
import org.dom4j.Document;

import java.io.File;

/**
 * Created by DreamInSun on 2018/1/17.
 */
public class ProjValidator {

    /*========== Properties ==========*/
    private FileMapping m_fileMapping;
    private SvcConfig m_svcConfig;

    /*========== Constructor ==========*/
    public ProjValidator(FileMapping fileMapping) {
        m_fileMapping = fileMapping;
    }

    /*========== Export Function ==========*/

    public void updateSvcConfig(SvcConfig svcConfig) {
        m_svcConfig = svcConfig;
    }

    public void validateProject() throws Exception {
        if (null == m_svcConfig) {
            throw new Exception("请先载入配置");
        }
        checkDeploy();
        checkRootPackageDir();
        checkJar();
        checkDefaultConfig();
        checkPom();
        checkDeployConfig();
    }

    /*========== Check  ==========*/
    public void checkRootPackageDir() throws Exception {
        String rootpackage = m_svcConfig.getRootPackage();
        /* Check if folder exists */
        File file = new File("./src/main/java/" + rootpackage.replace('.', '/'));
        if (!file.exists()) {
            throw new Exception("包名与工程名不对应");
        }
    }

    public void checkDefaultConfig() throws Exception {
        DefaultConfigEditor dfe = new DefaultConfigEditor(m_fileMapping.getDefaultConf());
        String rootpackage = m_svcConfig.getRootPackage();
        if (!(dfe.getProjectConfig().getRootPackage()).equals(rootpackage)) {
            throw new Exception("默认配置" + (m_fileMapping.getDefaultConf() + "中基础包名不对应，应为" + rootpackage));
        }
    }

    public void checkPom() throws Exception {
        PomEditor pe = new PomEditor(m_fileMapping.getMavenPom());
        Document doc = pe.loadFile();
        /*===== Check GroupID =====*/
        if(! PomEditor.getGroupId(doc).equals(m_svcConfig.getGroupId())){
            throw new Exception("POM配置/project/groupId不对应，应为" + m_svcConfig.getGroupId());
        }
        /*===== Check ArtifactID =====*/
        if(! PomEditor.getArtifactId(doc).equals(m_svcConfig.getArtifactId())){
            throw new Exception("POM配置/project/artifactID不对应，应为" + m_svcConfig.getArtifactId());
        }
        /*===== Check Version =====*/
        if(! PomEditor.getVersion(doc).equals(m_svcConfig.getApi_version())){
            throw new Exception("POM配置/project/version不对应，应为" + m_svcConfig.getApi_version());
        }
        /*===== Check BuildName =====*/
        String finalBuildName = "${project.name}-${project.version}";
        if(! PomEditor.getBuildName(doc).equals(finalBuildName)){
            throw new Exception("POM配置/project/build/finalName不对应，应为" + finalBuildName);
        }
    }

    public void checkDeploy() throws Exception {

    }

    public void checkDeployConfig() throws Exception {
        String filepath = m_fileMapping.getProductConf();
        File file = new File(filepath);
        if (!file.exists()) {
            throw new Exception("正式配置文件" + filepath + "不存在");
        }
    }

    public void checkJar() throws Exception {
        String filepath = "./target/" + m_svcConfig.getArtifactId() + '-' + m_svcConfig.getApi_version() + ".jar";
        File file = new File(filepath);

        if (!file.exists()) {
            throw new Exception("打包Jar文件" + filepath + "不存在");
        }
    }

    /*========== Assistant Function  ==========*/
    public boolean isFileExists(String filepath) {
        File file = new File(filepath);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }
}
