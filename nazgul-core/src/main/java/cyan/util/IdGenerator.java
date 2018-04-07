package cyan.util;

import cyan.nazgul.mngr.MybatisMngr;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import org.apache.commons.text.RandomStringGenerator;

/**
 * This utility class is used for variable ID generation.
 * Created by DreamInSun on 2017/8/28.
 */
public class IdGenerator {

    /*========== Properties ==========*/
    private static RandomStringGenerator g_randStrGen;

    public static RandomStringGenerator getRandStrGen() {
        if (g_randStrGen == null) {
            g_randStrGen = new RandomStringGenerator.Builder().withinRange('a', 'z').withinRange('A', 'Z').build();
        }
        return g_randStrGen;
    }

    /*========== Constructor ==========*/
    public IdGenerator() {
    }

    /*========== Static Export Functions ==========*/
    public static String getRandomStr(int length) {
        return getRandStrGen().generate(length);

    }

    /*========== Assistant Function ==========*/


}
