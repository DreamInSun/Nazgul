package cyan.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by DreamInSun on 2018/2/16.
 */
public class DecimalUtil {

    /*========== Properties ==========*/
    private static DecimalFormat df1 = new DecimalFormat("0.00");


    /*========== Export Function ==========*/
    public static String formatDecimal2Point(BigDecimal num) {
        String str = df1.format(num);
        return str;
    }

}
