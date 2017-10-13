package cyan.svc.nazgul.example.jobs;

import de.spinscale.dropwizard.jobs.Job;
import de.spinscale.dropwizard.jobs.annotations.Every;
import de.spinscale.dropwizard.jobs.annotations.OnApplicationStop;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jobs Demonstration: Routine Job
 * @see "https://github.com/spinscale/dropwizard-jobs"
 * Created by DreamInSun on 2017/10/13.
 */
@OnApplicationStop
public class StopJob extends Job {
    /*========== Logger ==========*/
    private static final Logger g_Logger = LoggerFactory.getLogger(StopJob.class);

    @Override
    public void doJob(JobExecutionContext context) throws JobExecutionException {
        // logic run once on application stop
        g_Logger.info("Jobs Stop!");
    }
}
