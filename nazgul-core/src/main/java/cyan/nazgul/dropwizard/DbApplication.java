package cyan.nazgul.dropwizard;

import com.google.common.collect.Lists;
import cyan.nazgul.dropwizard.component.*;
import cyan.nazgul.dropwizard.resources.IResource;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DreamInSun on 2016/7/21.
 */
@SuppressWarnings("unchecked")
public class DbApplication<TConfig extends DbConfiguration> extends BaseApplication<TConfig> {
    private static final Logger g_Logger = LoggerFactory.getLogger(DbApplication.class);

    /*========== Properties ==========*/

    /*========== Constructor ==========*/
    protected DbApplication(String[] args, Class configClass) {
        super(args);
        /* Db Management Component */
                  m_CompList.add(new MyBatisComponent<>(g_classRoot));
        m_CompList.add(new EntityManagerComponent<>(g_classRoot));
        m_CompList.add(new FlywayComponent<>(configClass));
        m_CompList.add(new RestEntityComponent<>());
        //m_CompList.add(new SpringContextComponent<>());
        m_CompList.add(new MultipleDataSourceComponent<>(g_classRoot));
    }

    /*========== Application Initialization ==========*/
    @Override
    public void initialize(Bootstrap<TConfig> bootstrap) {
        super.initialize(bootstrap);
    }

    @Override
    public void run(TConfig config, Environment env) throws Exception {
        super.run(config, env);
        /*===== Initialize Resource After Component is prepared =====*/
        for (IResource<TConfig> res : m_resourceList) {
            res.initialize(config, env);
        }
    }


}
