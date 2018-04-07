package cyan.nazgul.dropwizard.component;

import com.google.common.collect.Lists;
import com.scottescue.dropwizard.entitymanager.EntityManagerBundle;
import cyan.nazgul.docker.svc.EnvConfig;
import cyan.nazgul.dropwizard.DbConfiguration;
import cyan.nazgul.dropwizard.container.GlobalInstance;
import cyan.svc.entity.BaseEntity;
import cyan.util.clazz.ClassUtil;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * JPA支持
 * 会自动扫描rootpackage下，entities目录中的JPA对象，并注册。
 *
 * @see <a>http://scottescue.com/dropwizard-entitymanager/</a>
 * Created by DreamInSun on 2016/7/21.
 */
@SuppressWarnings("unchecked")
public class EntityManagerComponent<TConfig extends DbConfiguration> implements IComponent<TConfig> {
    private static Logger g_logger = LoggerFactory.getLogger(EntityManagerComponent.class);

    /*========== Properties ==========*/
    private EntityManagerBundle<TConfig> m_entityManagerBundle;
    private String m_packagePath;

    /*========== Constructor ==========*/
    public EntityManagerComponent(String packagePath) {
        m_packagePath = packagePath;
    }

    /*========== Interface : IComponent ==========*/
    @Override
    public void init(Bootstrap bootstrap) {
        /*===== Scan for entities =====*/
        List<Class<?>> entityList = scanEntity(m_packagePath + ".entities");
        Class[] entityClzArr = new Class[entityList.size()];
        entityList.toArray(entityClzArr);
        /*===== Register Entities =====*/
        if(entityClzArr != null && entityClzArr.length > 0) {
            m_entityManagerBundle = new EntityManagerBundle<TConfig>(BaseEntity.class, entityClzArr) {
                @Override
                public DataSourceFactory getDataSourceFactory(TConfig configuration) {
                    return configuration.getDataSourceFactory();
                }
            };
            bootstrap.addBundle(m_entityManagerBundle);
        }
    }

    @Override
    public void postInit(EnvConfig envConfig, Bootstrap<TConfig> bootstrap) {

    }

    @Override
    public void run(TConfig config, Environment environment) {
        if(m_entityManagerBundle != null){
            EntityManagerFactory entityManagerFactory = m_entityManagerBundle.getEntityManagerFactory();
            GlobalInstance.setEntityManagerFactory(entityManagerFactory);

            EntityManager entityManager = m_entityManagerBundle.getSharedEntityManager();
            GlobalInstance.setEntityManager(entityManager);
        }
    }


    /*==========  ==========*/

    /**
     * Search Resource class inhert from BaseResrouce, and register them.
     *
     * @param resPath resource class root package name
     */
    protected List<Class<?>> scanEntity(String resPath) {
        g_logger.info("\r\n\r\n/*========== Register Resources ===========*/\r\n");

        List<Class<?>> clzList = ClassUtil.getClassList(resPath, true, javax.persistence.Entity.class);

        List<Class<?>> entityClzList = Lists.newLinkedList();

        for (Class<?> entityClazz : clzList) {
            g_logger.info("Register Entity: " + entityClazz);
            /*========== Create Resource Instance ==========*/

            try {
                /* Patch for debug file */
                if (entityClazz.getName().endsWith("$1")) continue;
                entityClzList.add(entityClazz);
            } catch (Exception e) {
                g_logger.error(e.getMessage());
            }
        }
        return entityClzList;
    }
}
