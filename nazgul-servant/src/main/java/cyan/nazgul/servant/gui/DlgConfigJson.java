package cyan.nazgul.servant.gui;

import cyan.nazgul.servant.util.JsonUtil;
import javafx.scene.input.ClipboardContent;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;

public class DlgConfigJson extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea txtarea_config_string;
    private JButton btnCopyClipboard;

    /*========== Properties ==========*/
    String m_configStr;

    public DlgConfigJson() {
        setContentPane(contentPane);

        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        btnCopyClipboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copyClipboard();
            }
        });
    }

    private void onOK() {
// add your code here
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }


    /*========= Export Function ==========*/
    public void setConfigString(String configStr) {
        m_configStr = configStr;
        txtarea_config_string.setText(JsonUtil.format(configStr));
    }

    /*========= Assist Function ==========*/
    public void copyClipboard() {
        String get= m_configStr;
        StringSelection selec= new StringSelection(get);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selec, selec);
    }
}
