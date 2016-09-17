package cyan.util.clazz;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 用于获取指定包名下的所有类名.<br/>
 * 并可设置是否遍历该包名下的子包的类名.<br/>
 * 并可通过Annotation(内注)来过滤，避免一些内部类的干扰.<br/>
 *
 * @author Sodino E-mail:sodino@qq.com
 * @version Time：2014年2月10日 下午3:55:59
 */
public class ClassUtil {
    private static final org.slf4j.Logger g_Logger = org.slf4j.LoggerFactory.getLogger(ClassUtil.class);

    public static List<Class<?>> getClassList(String pkgName, boolean isRecursive, Class<? extends Annotation> annotation) {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            /* 按文件的形式去查找 */
            String strFile = pkgName.replaceAll("\\.", "/");
            g_Logger.info("Resource Package:\t" + strFile);
            Enumeration<URL> urls = loader.getResources(strFile);
            while (urls.hasMoreElements()) {
                String norlFilePath = URLDecoder.decode(urls.nextElement().toString(), "UTF-8");
                URL url = new URL(norlFilePath);
                System.out.println("File Path:" + norlFilePath);
                if (url != null) {
                    String protocol = url.getProtocol();
                    String pkgPath = URLDecoder.decode(url.getPath(), "UTF-8");
                    g_Logger.info("Protocol:[" + protocol + "]\tPath:" + pkgPath);
                    if ("file".equals(protocol)) {
                        /* 本地自己可见的代码 */
                        g_Logger.info("Load Classes From Own Package.");
                        findClassNameFromDir(classList, pkgName, pkgPath, isRecursive, annotation);
                    } else if ("jar".equals(protocol)) {
                        /* 引用第三方jar的代码 */
                        g_Logger.info("Load Classes From External JAR.");
                        findClassNameFromJar(classList, pkgName, url, isRecursive, annotation);
                    }
                }
            }
        } catch (IOException e) {
            g_Logger.error(e.getMessage());
        }

        return classList;
    }

    /**
     * Load Class File Mode
     *
     * @param clazzList
     * @param pkgName
     * @param pkgPath
     * @param isRecursive
     * @param annotation
     */
    public static void findClassNameFromDir(List<Class<?>> clazzList, String pkgName, String pkgPath, boolean isRecursive, Class<? extends Annotation> annotation) {

        if (clazzList == null) {
            return;
        }
        /*========== Normalize Package Path ==========*/
        if (pkgPath.startsWith("/")) {
            pkgPath = pkgPath.substring(1);
        }
        /*========== Find Class File ==========*/
        File[] files = filterClassFiles(pkgPath);// 过滤出.class文件及文件夹
        g_Logger.info("files:" + ((files == null) ? "null" : "length=" + files.length));
        if (files != null) {
            for (File f : files) {
                String fileName = f.getName();
                if (f.isFile()) {
                    // .class 文件的情况
                    String clazzName = getClassName(pkgName, fileName);
                    addClassName(clazzList, clazzName, annotation);
                } else {
                    // 文件夹的情况
                    if (isRecursive) {
                        // 需要继续查找该文件夹/包名下的类
                        String subPkgName = pkgName + "." + fileName;
                        String subPkgPath = pkgPath + "/" + fileName;
                        findClassNameFromDir(clazzList, subPkgName, subPkgPath, true, annotation);
                    }
                }
            }
        }
    }

    /**
     * Load class JAR mode.
     *
     * @param clazzList
     * @param pkgName
     * @param url
     * @param isRecursive
     * @param annotation
     * @throws IOException
     */
    public static void findClassNameFromJar(List<Class<?>> clazzList, String pkgName, URL url, boolean isRecursive, Class<? extends Annotation> annotation) throws IOException {
        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
        JarFile jarFile = jarURLConnection.getJarFile();
        Enumeration<JarEntry> jarEntries = jarFile.entries();
        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = jarEntries.nextElement();
            String jarEntryName = jarEntry.getName(); // 类似：sun/security/internal/interfaces/TlsMasterSecret.class
            String clazzName = jarEntryName.replace("/", ".");
            int endIndex = clazzName.lastIndexOf(".");

            String prefix = null;
            if (endIndex > 0) {
                String prefix_name = clazzName.substring(0, endIndex);
                endIndex = prefix_name.lastIndexOf(".");
                if (endIndex > 0) {
                    prefix = prefix_name.substring(0, endIndex);
                }
            }
            if (prefix != null && jarEntryName.endsWith(".class")) {

                if( clazzName.endsWith(".class")) {
                    clazzName = clazzName.substring(0, clazzName.length() - ".class".length());
                }

                if (prefix.equals(pkgName)) {
                    g_Logger.info("jar entryName:" + jarEntryName);
                    addClassName(clazzList, clazzName, annotation);
                } else if (isRecursive && prefix.startsWith(pkgName)) {
                    // 遍历子包名：子类
                    g_Logger.info("jar entryName:" + jarEntryName + " isRecursive:" + isRecursive);
                    addClassName(clazzList, clazzName, annotation);
                }
            }
        }
    }

    private static File[] filterClassFiles(String pkgPath) {
        if (pkgPath == null) {
            return null;
        }
        // 接收 .class 文件 或 类文件夹
        return new File(pkgPath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
    }

    private static String getClassName(String pkgName, String fileName) {
        int endIndex = fileName.lastIndexOf(".");
        String clazz = null;
        if (endIndex >= 0) {
            clazz = fileName.substring(0, endIndex);
        }
        String clazzName = null;
        if (clazz != null) {
            clazzName = pkgName + "." + clazz;
        }
        return clazzName;
    }

    private static void addClassName(List<Class<?>> clazzList, String clazzName, Class<? extends Annotation> annotation) {
        //g_Logger.info("addClassName:" + clazzName);
        if (clazzList != null && clazzName != null) {
            Class<?> clazz = null;
            try {
                //g_Logger.info("Class Name :" + clazzName);
                clazz = Class.forName(clazzName);
            } catch (ClassNotFoundException e) {
                g_Logger.error(e.getMessage());
            }

            if (clazz != null) {
                if (annotation == null) {
                    clazzList.add(clazz);
                    g_Logger.info("Find class: " + clazz);
                } else if (clazz.isAnnotationPresent(annotation)) {
                    clazzList.add(clazz);
                    g_Logger.info("Find annotation:" + clazz);
                }
            }
        }
    }
}
