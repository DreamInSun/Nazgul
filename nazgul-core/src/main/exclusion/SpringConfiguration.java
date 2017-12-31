package cyan.nazgul.dropwizard.config;

import cyan.nazgul.dropwizard.DbConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * TODO: this Spring Annotation not finished.
 *
 * @param <TAppConfiguration>
 */
@Configuration
@EnableTransactionManagement
//@ComponentScan(basePackageClasses = TodoDao.class)
public class SpringConfiguration<TAppConfiguration extends DbConfiguration> {

    /*========= Properties ==========*/
    //@Autowired
    private DataSource m_dataSource;

    //@Autowired
    private TAppConfiguration m_configuration;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(m_dataSource);
        //sessionFactory.setPackagesToScan(Todo.class.getPackage().getName());
        //sessionFactory.setPhysicalNamingStrategy(ImprovedNamingStrategy.INSTANCE);
        sessionFactory.setHibernateProperties(hibernateProperties());


        /*===== Hibernate 5 Create Session Factory =====*/
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure().build();
        Metadata metadata = new MetadataSources(standardRegistry)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(
                        ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();
        //SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties hibernateProperties() {
        // details elided...
        return null;
    }
}