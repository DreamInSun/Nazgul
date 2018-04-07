package cyan.util.time;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by DreamInSun on 2018/2/16.
 */
public class TimestampUtil {

    /*=========== Properties ===========*/
    private static Date g_date = new Date();
    private static SimpleDateFormat g_formatter = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss", Locale.CHINA);

    /*=========== Export Function ===========*/
    public static Timestamp getTsNow() {
        return new Timestamp(g_date.getTime());
    }

    public static String formatTime(Timestamp ts) {
        String payTime = g_formatter.format(ts);
        return payTime;
    }

    public static String formatTime(Date date) {
        String payTime = "";
        if (date != null) {
            payTime = g_formatter.format(date);
        }
        return payTime;
    }


    /**
     * Java将Unix时间戳转换成指定格式日期字符串
     * @param timestampString 时间戳 如："1473048265";
     * @param formats 要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
     *
     * @return 返回结果 如："2016-09-05 16:06:42";
     */
    public static String convertTimeStamp2Date(String timestampString, String formats) {
        if (formats == null || "".equals(formats))
            formats = "yyyy-MM-dd HH:mm:ss";
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        return date;
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param dateStr 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String convertDate2TimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return nowTimeStamp
     */
    public static String getNowTimeStamp() {
        long time = System.currentTimeMillis();
        String nowTimeStamp = String.valueOf(time / 1000);
        return nowTimeStamp;
    }


}
