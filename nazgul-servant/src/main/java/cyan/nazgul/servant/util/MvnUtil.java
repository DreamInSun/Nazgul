package cyan.nazgul.servant.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by DreamInSun on 2017/10/9.
 */
public class MvnUtil {
    public static void mvnPckage() {

        /*===== Get Current Dir =====*/
        String curPath = System.getProperty("user.dir");

        /*===== Execute Maven =====*/
        Runtime runtime = Runtime.getRuntime();
        /* Windows Command */
        String cmdStr = String.format("cmd /k cd /d %s && mvn package", curPath);
        try {
            Process process = runtime.exec(cmdStr);
            InputStream in = process.getInputStream();
            while (in.read() != -1) {
                System.out.println(in.read());
            }
            in.close();
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
