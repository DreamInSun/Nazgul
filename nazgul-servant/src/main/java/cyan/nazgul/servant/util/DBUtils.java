package cyan.nazgul.servant.util;

/**
 * Created by DreamInSun on 2017/10/26.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtils {
    //static String driver = "com.mysql.jdbc.Driver";
    //static String url = "jdbc:mysql://127.0.0.1:3306/test";//website  test
    static String url = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL";//website  test
    static String driver = "oracle.jdbc.driver.OracleDriver";
    //static String user = "root"; //mySql
    //static String password = "scott"; // mySql
    static String user = "scott";
    static String password = "scott";

    public static Connection getConnect() {
        Connection conn = null;
        try {
            // 加载驱动程序
            Class.forName(driver);
            // 连续数据库
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("getConnection success...");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConn(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*public static void main(String[] args) {
        getConnect();
    }*/
}
