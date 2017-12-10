package cyan.svc.nazgul.example.jobs;

import de.spinscale.dropwizard.jobs.Job;
import de.spinscale.dropwizard.jobs.annotations.Every;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jobs Demonstration: Routine Job
 * @see <a>https://github.com/spinscale/dropwizard-jobs"</a>
 * Created by DreamInSun on 2017/10/13.
 */
@Every("1min")
public class EveryTestJob extends Job {
    /*========== Logger ==========*/
    private static final Logger g_Logger = LoggerFactory.getLogger(EveryTestJob.class);
    @Override
    public void doJob(JobExecutionContext context) throws JobExecutionException {
        // logic run every time and time again
        g_Logger.info("Jobs Routine!");
    }
}
