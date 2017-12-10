package cyan.svc.nazgul.example.contract;


/**
 * This class define constant values used for this application.
 * Created by DreamInSun on 2017/9/14.
 */
public class Constant {
    /**
     * Patch for MySQL 5.7+, Timestamp cannot be null
     */
    public static final String TIMESTAMP_DEFAULT_DATETIME = "2000-01-01 00:00:00";
    public static final long TIMESTAMP_DEFAULT_VALUE = 946656000;
}
