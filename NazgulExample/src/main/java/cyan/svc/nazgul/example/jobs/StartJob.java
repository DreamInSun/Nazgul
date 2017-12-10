package cyan.svc.nazgul.example.jobs;

import de.spinscale.dropwizard.jobs.Job;
import de.spinscale.dropwizard.jobs.annotations.Every;
import de.spinscale.dropwizard.jobs.annotations.OnApplicationStart;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jobs Demonstration: Routine Job
 * @see <a>https://github.com/spinscale/dropwizard-jobs</a>
 * Created by DreamInSun on 2017/10/13.
 */
@OnApplicationStart
public class StartJob extends Job {
    /*========== Logger ==========*/
    private static final Logger g_Logger = LoggerFactory.getLogger(StartJob.class);

    @Override
    public void doJob(JobExecutionContext context) throws JobExecutionException {
        // logic run once on application start
        g_Logger.info("Jobs Start!");
    }
}