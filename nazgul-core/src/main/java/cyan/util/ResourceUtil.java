package cyan.util;


import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Util Class for
 * <p>
 * Created by DreamInSun on 2016/9/1.
 */
public class ResourceUtil {
    private final static Logger g_logger = LoggerFactory.getLogger(ResourceUtil.class);

    /*===== Static Properties=====*/
    private final static ClassLoader g_loader = Thread.currentThread().getContextClassLoader();

    private static String g_tempDir = null;

    private static String g_namespace = null;

    /*===== Getter & Setter =====*/
    public static String getG_tempDir() {
        return g_tempDir;
    }

    public static void setG_tempDir(String g_tempDir) {
        ResourceUtil.g_tempDir = g_tempDir;
    }

    public static String getG_namespace() {
        return g_namespace;
    }

    public static void setG_namespace(String g_namespace) {
        ResourceUtil.g_namespace = g_namespace;
    }

    /*========== Load File =========*/

    /**
     * 以File的形式加载资源文件，在File模式和Jar模式下都兼容。性能比loadResourceAsStream低。
     * 注意该方法会默认在系统缓存文件夹创建一个文件缓存，请自行处理缓存过期问题。
     * Windows下为：   %USERPROFILE%\AppData\Local\Temp\{$ServiceName}
     * Linux下位：     /tmp/{$ServiceName} 或/var/tmp/{$ServiceName}
     * 运行配置可修改缓存路径：java -Djava.io.tmpdir=/path/to/tmpdir
     *
     * @param path path of resource from root
     * @return
     * @see ResourceUtil#loadResourceAsStream(String)
     */
    public static File loadFile(String path) {
        /*===== Load Resource =====*/
        InputStream inputStream = loadResourceAsStream(path);
        /*===== Convert to File =====*/
        File file = null;
        try {
            file = getTempFile(path);
            inputStreamToFile(inputStream, file);
        } catch (IOException e) {
            g_logger.error(e.getMessage());
        }
       /*===== Return =====*/
        return file;
    }

    /**
     * 以InputStream的形式加载资源文件，在File模式和Jar模式下都兼容。
     *
     * @param path path of resource from root
     * @return
     */
    public static InputStream loadResourceAsStream(String path) {
        /*===== Format Path =====*/
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        path = normalizePath(path);
        /*===== Determin Root Path =====*/
        InputStream resStream = g_loader.getResourceAsStream(path);
        /*===== Return =====*/
        return resStream;
    }

    /*========== Assistant Function ==========*/
    public static String getJarPath(Class clazz) {
        /*===== Get Jar File Path =====*/
        String path = clazz.getProtectionDomain().getCodeSource().getLocation().getPath();
        //g_logger.debug("Load:" + path);
        g_logger.info("/*==========  ==========*/");
        g_logger.info("Load:" + path);
        try {
            path = java.net.URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /*===== Get Jar Directory Path =====*/
        int firstIndex = path.indexOf("/") + 1;
        int lastIndex = path.lastIndexOf("/") + 1;
        path = path.substring(firstIndex, lastIndex);
        /*===== Return =====*/
        return path;
    }

    /**
     * Description: ③ 封装了一个加载Jar包的方法，使用URL根据jar包加载规范获取输入流，并据此输入流做进一步的操作
     * </p>
     *
     * @param filePath
     * @throws IOException
     */
    public File loadFileFromJar(String filePath) throws IOException {
        // 注意在WINDOWS环境一定要使用正斜杠
        filePath = "jar:" + filePath;
        URL url = new URL(filePath);
//        JarURLConnection openStream = (JarURLConnection) url.openConnection();
//        InputStream inputStream = openStream.getInputStream();
        // 使用IO工具类将输入流输出到控制台
//        IOUtils.copy(inputStream, System.out);
        // 使用IO工具类从输入流中按行读取并存入List集合
//        List<String> contents = IOUtils.readLines(inputStream);
//        System.out.println(contents);

        File file = FileUtils.toFile(url);
        return file;
    }

    /*========== Assistant Function : File ==========*/

    /**
     * 标准化文件路径
     *
     * @param path
     * @return
     */
    public static String normalizePath(String path) {
        String ret = null;
        try {
            ret = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            g_logger.error(e.getMessage());
        }
        return ret;
    }

    /**
     * 将InputStream转为File
     *
     * @param ins
     * @param file
     */
    public static void inputStreamToFile(InputStream ins, File file) {
        /*==== Write File =====*/
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取缓存文件，若不存在则创建
     *
     * @param path 文件相对路径
     * @return
     * @throws IOException
     */
    public static File getTempFile(String path) throws IOException {
        /*===== Get Temp Directory Path =====*/
        String dirPath = getTempDirPath();
        /*===== Craete File =====*/
        String tempPath = normalizePath(dirPath + File.separator + path);
        File tempFile = new File(tempPath);
        /*===== Check Temp Directory =====*/
        File dirFile = tempFile.getParentFile();
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }
        /*===== Return =====*/
        return tempFile;
    }

    /**
     * 获取缓存文件夹路径
     *
     * @return
     */
    public static String getTempDirPath() {
        /*===== Get Config From Environment =====*/
        String ret = g_tempDir;
        /*===== Get =====*/
        if (null == ret) {
            ret = System.getProperty("java.io.tmpdir") + File.separator + g_namespace;
        }
        /*===== Check Writable =====*/
        File dirFile = new File(ret);
        if (!dirFile.canRead()) {
            g_logger.error("Temporary File [" + ret + "] can't write.");
        }
        /*===== Return =====*/
        return ret;
    }
}
