package cyan.nazgul.dropwizard.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DreamInSun on 2016/7/6.
 */
public class Database {
    static final Logger g_Logger = LoggerFactory.getLogger(Database.class);

    public boolean isConnected() {
        return true;
    }

    public String getUrl() {
        return "";
    }

    public void truncate() {
        g_Logger.info("Turncate Database.");
    }
}
