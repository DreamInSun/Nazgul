package cyan.nazgul.servant.gui;

import cyan.nazgul.servant.editor.DefaultConfigEditor;
import cyan.nazgul.servant.editor.DockerEnvEditor;
import cyan.nazgul.servant.editor.DockerfileEditor;
import cyan.nazgul.servant.editor.PomEditor;
import cyan.nazgul.servant.entity.FileMapping;
import cyan.nazgul.servant.entity.ProjectConfig;
import cyan.nazgul.servant.entity.SvcConfig;
import cyan.nazgul.servant.onering.ConfigConvertor;
import cyan.nazgul.servant.util.FileUtil;
import cyan.nazgul.servant.util.JsonUtil;
import cyan.nazgul.servant.validator.ProjValidator;

import javax.swing.*;

/**
 * Created by DreamInSun on 2017/10/8.
 */
public class FrmServantMain {


    /*========== GUI Properties ==========*/
    private JButton btn_update_setting;
    private JButton btn_load_setting;
    private JTextField txt_svc_name;
    private JTextField txt_svc_version;
    private JTextField txt_profile;
    private JTextField txt_conf_conn;
    private JTextField txt_conn_key;
    private JTextField txt_api_version;
    private JLabel lbl_svc_name;
    private JLabel lbl_svc_version;
    private JLabel lbl_profile;
    private JLabel lbl_conf_conn;
    private JLabel lbl_conf_key;
    private JLabel lbl_api_key;
    private JPanel pnl_svc_info;
    private JPanel pnl_main;
    private JButton btnMvnPackage;
    private JTextField lbl_working_dir;
    private JButton btn_deploy;
    private JButton btnGenDevConfigJson;
    private JButton btnGenProductConfigJson;
    private JButton btnValidateConfig;
    private JButton btnEditHistory;

    /*========== Child Dialog ==========*/


    /*========== Properties ==========*/
    private FileMapping m_fileMapping;
    private SvcConfig m_SvcConfig;
    private ProjectConfig m_projectConfig;
    private DockerfileEditor m_dockerfileEditor;
    private PomEditor m_pomEditor;
    private DockerEnvEditor m_dockerEnvEditor;
    private DefaultConfigEditor m_defaultConfigEditor;
    private ConfigConvertor m_configConvertor;
    private ProjValidator m_projValidator;

    /*========== GUI Listener ==========*/

    /*========== Constructor ==========*/
    public FrmServantMain() {
        SwingUtilities.invokeLater(() -> init());
    }

    protected void init() {
           /*===== Prepare Editor =====*/
        m_fileMapping = FileUtil.loadFileMapping();
        m_dockerfileEditor = new DockerfileEditor(m_fileMapping.getDockerfile());
        m_pomEditor = new PomEditor(m_fileMapping.getMavenPom());
        m_dockerEnvEditor = new DockerEnvEditor(m_fileMapping.getDockerEnv());
        m_defaultConfigEditor = new DefaultConfigEditor(m_fileMapping.getDefaultConf());
        m_configConvertor = new ConfigConvertor(m_fileMapping);
        m_projValidator = new ProjValidator(m_fileMapping);

        /*===== Load SvcConfig =====*/
        m_SvcConfig = m_dockerfileEditor.loadSvcConfig();

        /*===== Working Directory =====*/
        String workingDir = System.getProperty("user.dir");
        lbl_working_dir.setText(workingDir);

        /*===== Event Listener =====*/
        btn_load_setting.addActionListener(actionListener -> {
            m_SvcConfig = m_dockerfileEditor.loadSvcConfig();
            m_projectConfig = m_defaultConfigEditor.getProjectConfig();
            m_projValidator.updateSvcConfig(m_SvcConfig);
            fillSvcConfig(m_SvcConfig);

        });
        btn_update_setting.addActionListener(actionListener -> {
            updateSvcConfigFromUI();
            saveSvcConfig();
        });
        btnMvnPackage.addActionListener(actionListener -> {
            DlgConsole dlgConsole = new DlgConsole();
            dlgConsole.setBounds(200, 200, 800, 600);
            dlgConsole.setVisible(true);

            //Runnable run = () -> MvnUtil.mvnPckage(value -> dlgConsole.printLine(value));
            //new Thread(run).start();


        });

        btnGenDevConfigJson.addActionListener(actionListener -> {
            String configItemStr = m_configConvertor.getDevelConfigJson();
            DlgConfigJson dlgConfigJson = new DlgConfigJson();
            dlgConfigJson.setBounds(200, 200, 800, 800);
            dlgConfigJson.setConfigString(JsonUtil.format(configItemStr));
            dlgConfigJson.setVisible(true);
        });

        btnGenProductConfigJson.addActionListener(actionListener -> {
            String configItemStr = m_configConvertor.getProductConfigJson();
            DlgConfigJson dlgConfigJson = new DlgConfigJson();
            dlgConfigJson.setBounds(200, 200, 800, 600);
            dlgConfigJson.setConfigString(configItemStr);
            dlgConfigJson.setVisible(true);
        });

        btnValidateConfig.addActionListener(e -> {
            try {
                m_projValidator.validateProject();
                JOptionPane.showMessageDialog(null, "部署校验通过", "校验成功", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                String msg = ex.getMessage();
                JOptionPane.showMessageDialog(null, msg, " 校验失败 ", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    /*========== Main ==========*/
    public static void main(String[] args) {

        /*===== Start =====*/
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("FrmServantMain");
            frame.setContentPane(new FrmServantMain().pnl_main);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });

    }

    /*========== Assatant Function ==========*/
    protected void loadSvcConfig() {
        m_SvcConfig = m_dockerfileEditor.loadSvcConfig();
    }

    protected void saveSvcConfig() {
        m_defaultConfigEditor.saveFile(m_SvcConfig);
        m_dockerfileEditor.saveSvcConfig(m_SvcConfig);
        m_pomEditor.saveFile(m_SvcConfig);
        m_dockerEnvEditor.saveFile(m_SvcConfig);
    }

    protected void fillSvcConfig(SvcConfig svcConfig) {
        txt_svc_name.setText(svcConfig.getSvc_name());
        txt_svc_version.setText(svcConfig.getSvc_version());
        txt_profile.setText(svcConfig.getProfile());
        txt_conf_conn.setText(svcConfig.getConf_conn());
        txt_conn_key.setText(svcConfig.getConf_key());
        txt_api_version.setText(svcConfig.getApi_version());
    }

    protected void updateSvcConfigFromUI() {
        m_SvcConfig.setSvc_name(txt_svc_name.getText());
        m_SvcConfig.setSvc_version(txt_svc_version.getText());
        m_SvcConfig.setProfile(txt_profile.getText());
        m_SvcConfig.setConf_conn(txt_conf_conn.getText());
        m_SvcConfig.setConf_key(txt_conn_key.getText());
        m_SvcConfig.setApi_version(txt_api_version.getText());
    }

}
