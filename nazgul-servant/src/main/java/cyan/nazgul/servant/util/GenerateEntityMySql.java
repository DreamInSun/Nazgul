package cyan.nazgul.servant.util;

/**
 * Created by DreamInSun on 2017/10/26.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

/**
 * 功能：根据MySql表格生成对应的Java实体类
 * 表格的格式如下：
 * 表名：xx(*)_xx(*)_user(user前面的如果还有，就要以_分开，实体类是获取_后面的字符全名)
 * 或是：user直接为表名
 * 属性名如：userName或是user_name
 *
 * @author weisg
 */
public class GenerateEntityMySql {
    /*==========  ==========*/
    private String packageOutPath = "com.user.entity";//指定实体生成所在包的路径
    private String authorName = "DreamInSun";//作者名字
    private String tablename = "t_user";//表名
    private String[] colnames; // 列名数组
    private String[] colTypes; //列名类型数组
    private int[] colSizes; //列名大小数组
    private boolean f_util = false; // 是否需要导入包java.util.*
    private boolean f_sql = false; // 是否需要导入包java.sql.*

    /*========== Constructor ==========*/
    public GenerateEntityMySql() {
        //创建连接
        Connection conn;
        //查要生成实体类的表
        String sql = "select * from " + tablename;
        PreparedStatement pStemt = null;
        conn = DBUtils.getConnect();
        ResultSetMetaData rsmd;
        try {
            pStemt = conn.prepareStatement(sql);
            rsmd = pStemt.getMetaData();
            int size = rsmd.getColumnCount();   //统计列
            colnames = new String[size];
            colTypes = new String[size];
            colSizes = new int[size];
            for (int i = 0; i < size; i++) {
                colnames[i] = rsmd.getColumnName(i + 1).toLowerCase();
                colTypes[i] = rsmd.getColumnTypeName(i + 1).toLowerCase();
                if (colTypes[i].equalsIgnoreCase("datetime") || colTypes[i].equalsIgnoreCase("date")) {
                    f_util = true;
                }
                if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {
                    f_sql = true;
                }
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }
            String content = parse();
            File directory = new File("");
            String path = this.getClass().getResource("").getPath();
            System.out.println(path);
            String outputPath = directory.getAbsolutePath() + "/src/" + this.packageOutPath.replace(".", "/") + "/" + initcap(handleEntityName(tablename)) + ".java";
            File file = new File(outputPath);
            if (!file.getParentFile().exists()) {
                //如果目标文件所在的目录不存在，则创建父目录
                //System.out.println("目标文件所在目录不存在，要创建它！");
                if (!file.getParentFile().mkdirs()) {
                    System.out.println("创建目标文件所在目录失败！");
                    return;
                }
            }
            FileWriter fw = new FileWriter(outputPath);

            PrintWriter pw = new PrintWriter(fw);
            pw.println(content);
            pw.flush();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConn(null, pStemt, conn);
        }
    }

    /*==========  ==========*/
    private String handleEntityName(String tablename) {
        String name = tablename;
        if (tablename.indexOf("_") > 0) {
            int tableNameLength = tablename.split("_").length;
            name = tablename.split("_")[tableNameLength - 1];
        }
        return name;
    }

    /**
     * 功能：生成实体类主体代码
     *
     * @return
     * @author weisg
     */
    private String parse() {
        StringBuffer sb = new StringBuffer();
        sb.append("package " + this.packageOutPath + ";\r\n");
        //判断是否导入工具包
        if (f_util) {
            sb.append("import java.util.Date;\r\n");
        }
        if (f_sql) {
            sb.append("import java.sql.*;\r\n");
        }

        sb.append("\r\n");
        //注释部分
        sb.append("   /**\r\n");
        sb.append("    * " + handleEntityName(tablename) + " 实体类\r\n");
        sb.append("    * " + new Date() + " " + this.authorName + "\r\n");
        sb.append("    */ \r\n");
        //实体部分
        sb.append("\r\n\r\npublic class " + initcap(handleEntityName(tablename)) + "{\r\n");
        processAllAttrs(sb);//属性
        processAllMethod(sb);//get set方法
        sb.append("}\r\n");
        return sb.toString();
    }

    private String handleAttr(String attr) {
        if (attr.indexOf("_") > 0) {
            String[] arr = attr.split("_");
            String nameTemp = "";
            nameTemp = arr[0];
            for (int i = 1; i < arr.length; i++) {
                nameTemp += initcap(arr[i]);
            }
            attr = nameTemp;
        }
        return attr;
    }

    /**
     * 功能：生成所有属性
     *
     * @param sb
     */
    private void processAllAttrs(StringBuffer sb) {
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + handleAttr(colnames[i]) + ";\r\n");
        }
    }

    /**
     * 功能：生成所有方法
     *
     * @param sb
     */
    private void processAllMethod(StringBuffer sb) {
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tpublic void set" + initcap(handleAttr(colnames[i])) + "(" + sqlType2JavaType(colTypes[i]) + " " + handleAttr(colnames[i]) + "){\r\n");
            sb.append("\t\tthis." + handleAttr(colnames[i]) + "=" + handleAttr(colnames[i]) + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(handleAttr(colnames[i])) + "(){\r\n");
            sb.append("\t\treturn " + handleAttr(colnames[i]) + ";\r\n");
            sb.append("\t}\r\n");
        }
    }

    /**
     * 功能：将输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    private String initcap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 功能：获得列的数据类型
     *
     * @param sqlType
     * @return
     */
    private String sqlType2JavaType(String sqlType) {
        if (StringUtils.isNotBlank(sqlType)) {

            if (sqlType.equalsIgnoreCase("bit")) {
                return "boolean";
            } else if (sqlType.equalsIgnoreCase("tinyint")) {
                return "byte";
            } else if (sqlType.equalsIgnoreCase("smallint")) {
                return "short";
            } else if (sqlType.equalsIgnoreCase("int")) {
                return "int";
            } else if (sqlType.equalsIgnoreCase("bigint")) {
                return "long";
            } else if (sqlType.equalsIgnoreCase("float")) {
                return "float";
            } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric") || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money") || sqlType.equalsIgnoreCase("smallmoney")) {
                return "double";
            } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char") || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar") || sqlType.equalsIgnoreCase("text")) {
                return "String";
            } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("date")) {
                return "Date";
            } else if (sqlType.equalsIgnoreCase("image")) {
                return "Blod";
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new GenerateEntityMySql();
        //File directory = new File("");
        //String path=this.getClass().getResource("").getPath();
    }
}



