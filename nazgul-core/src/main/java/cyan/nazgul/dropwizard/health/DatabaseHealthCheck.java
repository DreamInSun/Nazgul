package cyan.nazgul.dropwizard.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by DreamInSun on 2016/7/6.
 */
public class DatabaseHealthCheck extends HealthCheck {
    private final Database database;

    public DatabaseHealthCheck(Database database) {
        this.database = database;
    }

    @Override
    protected Result check() throws Exception {
        if (database.isConnected()) {
            return Result.healthy();
        } else {
            return Result.unhealthy("Cannot connect to " + database.getUrl());
        }
    }
}