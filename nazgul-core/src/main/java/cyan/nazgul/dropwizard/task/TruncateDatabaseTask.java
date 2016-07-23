package cyan.nazgul.dropwizard.task;

import com.google.common.collect.ImmutableMultimap;
import cyan.nazgul.dropwizard.health.Database;
import io.dropwizard.servlets.tasks.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;

/**
 * Created by DreamInSun on 2016/7/6.
 */
public class TruncateDatabaseTask extends Task {
    static final Logger g_Logger = LoggerFactory.getLogger(TruncateDatabaseTask.class);

    private final Database database;

    public TruncateDatabaseTask(Database database) {
        super("truncate");
        this.database = database;
    }

    @Override
    public void execute(ImmutableMultimap<String, String> parameters, PrintWriter output) throws Exception {
        g_Logger.info("Turncat Database.");
        this.database.truncate();
    }


}
