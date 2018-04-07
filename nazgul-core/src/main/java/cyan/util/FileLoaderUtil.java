package cyan.util;

import cyan.nazgul.dropwizard.BaseApplication;

import java.io.*;

/**
 * Created by DreamInSun on 2018/3/31.
 */
public class FileLoaderUtil {

    /**
     * @param path
     * @param appClass 如果有传该参数，则会搜索Jar相对路径，Resource相对路径
     * @return
     */
    public static File loadFileAutomatic(String path, Class<? extends BaseApplication> appClass) throws FileNotFoundException {
        String realPath = path;
           /*===== Load File =====*/
        InputStream fileInputStream = null;
        File file = new File(path);
        if (file.exists()) {
            System.out.println("Load Config File Absolutely: " + path);
        } else {
            String relativePath = getJarPath(appClass) + path;
            file = new File(relativePath);
            if (file.exists()) {
                System.out.println("Load Config File Jar Relative: " + relativePath);
            } else {
                String workingDirPath = getWorkingDirectory() + path;
                file = new File(workingDirPath);
                if (file.exists()) {
                    System.out.println("Load Config File WorkingDir Relative: " + workingDirPath);
                } else {
                /* Load From Resource */
                    System.out.println("Load Config File from Resource: " + path);
                    fileInputStream = appClass.getResourceAsStream(path);
                    if (fileInputStream == null) {
                        throw new FileNotFoundException("Could not find file [" + path + "] in both absolute path and resource path.");
                    }
                }
            }

        }
        return file;
    }

    public static String getWorkingDirectory() {
        String currentDir = System.getProperty("user.dir");
        /*=====  =====*/
        return currentDir;
    }


    /**
     * @param appClass 执行的Application类，继承自BaseApplication
     * @return
     */
    public static String getJarPath(Class<? extends BaseApplication> appClass) {
        /*===== Get Jar File Path =====*/
        String path = appClass.getProtectionDomain().getCodeSource().getLocation().getPath();
        try {
            path = java.net.URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /*===== Get Jar Directory Path =====*/
        int firstIndex = path.indexOf("/") + 1;
        int lastIndex = path.lastIndexOf("/");
        path = path.substring(firstIndex, lastIndex);
        /*===== Return =====*/
        return path;
    }


}
