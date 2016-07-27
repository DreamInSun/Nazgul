package cyan.nazgul.dropwizard;

import cyan.nazgul.dropwizard.component.FlywayComponent;
import cyan.nazgul.dropwizard.component.IComponent;
import cyan.nazgul.dropwizard.component.MyBatisComponent;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DreamInSun on 2016/7/21.
 */
public class DbApplication<TConfig extends DbConfiguration> extends BaseApplication<TConfig> {
    private static final Logger g_Logger = LoggerFactory.getLogger(DbApplication.class);

    /*========== Properties ==========*/
    protected List<IComponent<TConfig>> m_DbCompList = new ArrayList<>();

    /*========== Constructor ==========*/
    protected DbApplication(String[] args, Class configClass) {
        super(args);
        /* Db Management Component */
        m_DbCompList.add(new MyBatisComponent<>(g_classRoot));
        m_DbCompList.add(new FlywayComponent<>(configClass));
    }

    /*========== Application Initialization ==========*/
    @Override
    public void initialize(Bootstrap<TConfig> bootstrap) {
        super.initialize(bootstrap);
        /*===== Register DbComponent =====*/
        for (IComponent comp : m_DbCompList) {
            comp.init(bootstrap);
        }
    }

    @Override
    public void run(TConfig config, Environment env) throws Exception {
        super.run(config, env);
        for (IComponent comp : m_DbCompList) {
            comp.run(config, env);
        }
    }


}
