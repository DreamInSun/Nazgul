package cyan.nazgul.servant.util;

import java.io.*;

/**
 * Created by DreamInSun on 2017/10/9.
 */
public class MvnUtil {
    /*========== Static Properties ==========*/
    public static boolean g_isRunning = false;

    /*==========  ==========*/
    public interface IRunCommand {
        void println(String value);
    }

    public static void mvnPckage(IRunCommand runCmd) {
        if( g_isRunning == true) return;
        /*===== Get Current Dir =====*/
        String curPath = System.getProperty("user.dir");

        /*===== Execute Maven =====*/
        Runtime runtime = Runtime.getRuntime();
        /* Windows Command */
        String cmdStr = String.format("cmd /k cd /d %s && mvn -Dmaven.compile.fork=true -DskipTests=true package", curPath);
        try {
            g_isRunning = true;
            Process process = runtime.exec(cmdStr);
            BufferedInputStream inputStream = new BufferedInputStream(
                    process.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream));
            String line;
            while (bufferedReader.read() != -1) {
                line = bufferedReader.readLine();
                if (null != runCmd) {
                    runCmd.println(line);
                }
                System.out.println(line);
            }
            bufferedReader.close();
            inputStream.close();
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        g_isRunning = false;
    }
}
