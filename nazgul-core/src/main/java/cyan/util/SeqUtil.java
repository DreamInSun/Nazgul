package cyan.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DreamInSun on 2018/2/13.
 */
public class SeqUtil {
    public static String getPayOrderSeq() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(new Date());
        return "CAR_" + dateString;
    }
}
