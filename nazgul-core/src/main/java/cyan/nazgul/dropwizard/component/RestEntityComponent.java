package cyan.nazgul.dropwizard.component;

import com.google.common.collect.Maps;
import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.DbConfiguration;
import cyan.nazgul.dropwizard.config.ProjectConfig;
import cyan.nazgul.dropwizard.resources.GenericEntityResource;
import cyan.nazgul.dropwizard.resources.IResource;
import cyan.util.clazz.ClassUtil;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 扫描指定路径，提供指定实体类型的CURDL
 * Created by DreamInSun on 2018/1/1.
 */
public class RestEntityComponent<TConfig extends DbConfiguration> implements IComponent<TConfig> {
    public static final Logger g_logger = LoggerFactory.getLogger(RestEntityComponent.class);

    /*========= Properties ==========*/
    private static ProjectConfig m_projectConfig;
    private static String m_entityPackage;

    /*========= Interface : IComponent ==========*/
    @Override
    public void init(Bootstrap bootstrap) {
        bootstrap.addBundle(new MultiPartBundle());
    }

    @Override
    public void postInit(EnvConfig envConfig, Bootstrap<TConfig> bootstrap) {

    }

    @Override
    public void run(TConfig config, Environment environment) {
        /*===== Scan Entities =====*/
        m_projectConfig = config.getProjectConfig();
        m_entityPackage = m_projectConfig.getRootPackage() + ".entities";
        /*===== Register REST Resource =====*/
        environment.jersey().register(new GenericEntityResource(config, environment));
    }

}
