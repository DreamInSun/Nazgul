package cyan.util;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by DreamInSun on 2016/9/20.
 */
public class FileOperation {
    private static final Logger g_logger = LoggerFactory.getLogger(FileOperation.class);

    public static String saveInputSteam(InputStream inputStream, String saveDir, String saveFileName) {
        try {
            /* Open OutputStream */
            FileUtils.forceMkdir(FileUtils.getFile(saveDir));
            File saveFile = FileUtils.getFile(saveDir, saveFileName);

            OutputStream outputStream = FileUtils.openOutputStream(saveFile);
            /* Write File */
            int length = 0;
            byte[] buff = new byte[256];
            while (-1 != (length = inputStream.read(buff))) {
                outputStream.write(buff, 0, length);
            }
            /* Finish */
            inputStream.close();
            outputStream.close();
            return saveFile.getAbsolutePath();
        } catch (FileNotFoundException e) {
            g_logger.error(e.getMessage());
        } catch (IOException e) {
            g_logger.error(e.getMessage());
        }
        return null;
    }

}
