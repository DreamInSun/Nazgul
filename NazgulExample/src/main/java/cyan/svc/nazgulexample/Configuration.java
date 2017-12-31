package cyan.svc.nazgulexample;

import com.fasterxml.jackson.annotation.JsonProperty;
import cyan.nazgul.dropwizard.DbConfiguration;
import cyan.svc.nazgulexample.config.SuperAdminConfig;
import de.spinscale.dropwizard.jobs.JobConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Collections;
import java.util.Map;

/**
 * Customized Application Configuration
 * - You can add your config here
 * - You can use BaseConfiguration as base is you don't need a default database, and use MySqlMngr in Resource instead.
 * Created by DreamInSun on 2016/6/30.
 */
public class Configuration extends DbConfiguration<Configuration> implements JobConfiguration {

    /*========== Properties ==========*/
    @JsonProperty("superadmin")
    private SuperAdminConfig superAdminConfig;

    /*========== Getter & Setter ==========*/
    public SuperAdminConfig getSuperAdminConfig() {
        return superAdminConfig;
    }

    public void setSuperAdminConfig(SuperAdminConfig superAdminConfig) {
        this.superAdminConfig = superAdminConfig;
    }

    /*========== Implements : DbConfiguration ==========*/


    /*========== Implements : JobConfiguration ==========*/
    public Map<String, String> getJobs() {
        return Collections.emptyMap();
    }
}