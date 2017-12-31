package cyan.svc.nazgulexample.jobs;

import cyan.nazgul.dropwizard.container.GlobalInstance;
import cyan.svc.nazgulexample.mappers.SqlMapper;
import de.spinscale.dropwizard.jobs.Job;
import de.spinscale.dropwizard.jobs.annotations.DelayStart;
import de.spinscale.dropwizard.jobs.annotations.Every;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DreamInSun on 2017/10/30.
 */
@DelayStart("2h")
@Every("1h")
public class DbHeartbeatJob extends Job {
    private static Logger g_logger = LoggerFactory.getLogger(DbHeartbeatJob.class);

    @Override
    public void doJob(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        try (SqlSession sqlSession = GlobalInstance.getSqlSessionFactory().openSession()) {
            SqlMapper sqlMapper = sqlSession.getMapper(SqlMapper.class);
            sqlMapper.heartBeat();
            sqlSession.close();
        } catch (Exception e) {
            g_logger.error(e.getMessage());
        }
    }
}
