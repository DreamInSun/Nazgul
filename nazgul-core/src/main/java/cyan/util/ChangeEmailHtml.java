package cyan.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by Administrator on 2018/2/11.
 */
public class ChangeEmailHtml {
    public static final Logger g_Logger = LoggerFactory.getLogger(ChangeEmailHtml.class);

    public static String changeHtml(String orderType, String orderTel, String orderNumber, String payAmont, String payTime) {
        StringBuilder stringHtml = new StringBuilder();
        /*===== 打开模板文件 =====*/
        try {
            PrintStream printStream = new PrintStream(new FileOutputStream("myorder.html"));
        } catch (FileNotFoundException e) {
            g_Logger.error(e.getMessage());
        }
        /*===== 输入HTML文件内容 =====*/
        stringHtml.append("<html><head>");
        stringHtml.append("<meta http-equiv=\"Content-Type\" content=\"myorder/html; charset=GBK\">");
        stringHtml.append("</head>");
        stringHtml.append("<body>");
        stringHtml.append("<div style='text-align:center;font-size:20px;color:red'>订单详情</div>");
        stringHtml.append("<div>订单类型:" + orderType + "</div>");
        stringHtml.append("<div>联系电话:" + orderTel + "</div>");
        stringHtml.append("<div>车牌号:" + orderNumber + "</div>");
        stringHtml.append("<div>订单金额:" + payAmont + "</div>");
        stringHtml.append("<div>下单时间:" + payTime + "</div>");
        stringHtml.append("</body></html>");
        /*===== Return =====*/
        String outputStr = stringHtml.toString();
        g_Logger.debug(outputStr);
        return outputStr;
    }
}
