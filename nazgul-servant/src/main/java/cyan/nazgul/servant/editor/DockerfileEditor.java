package cyan.nazgul.servant.editor;

import cyan.nazgul.servant.entity.SvcConfig;

import java.io.*;

/**
 * Created by DreamInSun on 2017/10/9.
 */
public class DockerfileEditor {

    /*========== Properties ==========*/
    protected String m_filePath;

    /*========== Constructor ==========*/
    public DockerfileEditor(String filePath) {
        this.m_filePath = filePath;
    }

    /*========== Export Function ==========*/
    public SvcConfig loadSvcConfig() {
        SvcConfig svcConfig = new SvcConfig();
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(new File(this.m_filePath)));
            String line = bufReader.readLine();
        /*===== Replace With File =====*/
            while (line != null) {
            /* Destucture Command */
                String[] cmdArr = line.split("\\s+");
                if ("ENV".equals(cmdArr[0])) {
                    if (cmdArr.length > 2 && !cmdArr[1].isEmpty()) {
                        switch (cmdArr[1]) {
                            case "SERVICE_NAME":
                                svcConfig.setSvc_name(cmdArr[2]);
                                break;
                            case "SERVICE_VERSION":
                                svcConfig.setSvc_version(cmdArr[2]);
                                break;
                            case "PROFILE":
                                svcConfig.setProfile(cmdArr[2]);
                                break;
                            case "CONFIG_CONN":
                                svcConfig.setConf_conn(cmdArr[2]);
                                break;
                            case "CONFIG_KEY":
                                svcConfig.setConf_key(cmdArr[2]);
                                break;
                            case "API_VERSION":
                                svcConfig.setApi_version(cmdArr[2]);
                                break;
                        }
                    }
                }
                line = bufReader.readLine();
            }
            bufReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return svcConfig;
    }


    public void saveSvcConfig(SvcConfig svcConfig) {
        try {
            /*==== Prepare =====*/
            File inFile = new File(m_filePath);
            File outFile = new File(m_filePath + ".out");
            BufferedReader bufReader = new BufferedReader(new FileReader(inFile));
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter(outFile));
            /*===== Scan && Replace =====*/
            String line = bufReader.readLine();
            while (line != null) {
            /* Destucture Command */
                String[] cmdArr = line.split("\\s+");
                if ("ENV".equals(cmdArr[0])) {
                    if (null != cmdArr[1]) {
                        switch (cmdArr[1]) {
                            case "SERVICE_NAME":
                                line = "ENV SERVICE_NAME      " + svcConfig.getSvc_name();
                                break;
                            case "SERVICE_VERSION":
                                line = "ENV SERVICE_VERSION   " + svcConfig.getSvc_version();
                                break;
                            case "PROFILE":
                                line = "ENV PROFILE           " + svcConfig.getProfile();
                                break;
                            case "CONFIG_CONN":
                                line = "ENV CONFIG_CONN       " + svcConfig.getConf_conn();
                                break;
                            case "CONFIG_KEY":
                                line = "ENV CONFIG_KEY        " + svcConfig.getConf_key();
                                break;
                            case "API_VERSION":
                                line = "ENV API_VERSION       " + svcConfig.getApi_version();
                                break;
                        }
                    }
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
            contentCopy(outFile, inFile);
            outFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void contentCopy(File inFile, File outFile) throws IOException {
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
