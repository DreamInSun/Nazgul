package cyan.nazgul.dropwizard.component;

import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.BaseConfiguration;
import cyan.util.clazz.ClassUtil;
import de.spinscale.dropwizard.jobs.Job;
import de.spinscale.dropwizard.jobs.JobsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * 定时任务组件
 *
 * @see <a>https://github.com/spinscale/dropwizard-jobs</a>
 * Created by DreamInSun on 2017/9/15.
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class JobComponent<TConfig extends BaseConfiguration> implements IComponent<TConfig> {

    /*========== Logger ==========*/
    private static final Logger g_Logger = LoggerFactory.getLogger(JobComponent.class);

    /*========== Properties ==========*/
    private String m_classRoot;
    private JobsBundle m_jobsBunlde;

    /*========== Constructor ==========*/
    public JobComponent(String classRoot) {
        m_classRoot = classRoot;
    }

    /*========== Interface : IComponent ==========*/
    @Override
    public void init(Bootstrap bootstrap) {
        /*===== Scan Jobs =====*/

        /*===== Create Jobs ====*/
        List<Job> jobList = scanJobs(m_classRoot + ".jobs");
        if (jobList != null && jobList.size() > 0) {
            Job[] jobs = new Job[jobList.size()];
            jobList.toArray(jobs);
            m_jobsBunlde = new JobsBundle(jobs);
            bootstrap.addBundle(m_jobsBunlde);
        }
    }

    @Override
    public void postInit(EnvConfig envConfig, Bootstrap<TConfig> bootstrap) {

    }

    @Override
    public void run(TConfig config, Environment environment) {
        //TODO
    }

    /*==========  ==========*/

    protected List<Job> scanJobs(String resPath) {
        g_Logger.info("## Scan Jobs");
        List<Class<?>> clzList = ClassUtil.getClassList(resPath, false, null);
        List<Job> jobList = new LinkedList<>();

        for (Class<?> resClazz : clzList) {
            g_Logger.info("Register Jobs: " + resClazz);
            /*========== Create Resource Instance ==========*/
            Object resInstance;
            try {
                /* Patch for debug file */
                if (resClazz.getName().endsWith("$1")) continue;
                Class c = Class.forName(resClazz.getName());
                java.lang.reflect.Constructor constructor = c.getConstructor();
                resInstance = constructor.newInstance();
                /* Add to Resource List */
                jobList.add((Job) resInstance);
            } catch (Exception e) {
                g_Logger.error(e.getMessage());
            }
        }
        return jobList;
    }

}
