package cyan.svc.nazgulexample;

import com.fasterxml.jackson.annotation.JsonProperty;
import cyan.nazgul.dropwizard.DbConfiguration;
import cyan.svc.nazgulexample.config.SvcConfig;

/**
 * Customized Application Configuration
 * - You can add your config here
 * - You can use BaseConfiguration as base is you don't need a default database, and use MySqlMngr in Resource instead.
 * Created by DreamInSun on 2016/6/30.
 */
public class Configuration extends DbConfiguration<Configuration, SvcConfig> {

    /*========== Properties ==========*/

    /*========== Getter & Setter ==========*/


    /*========== Implements : DbConfiguration ==========*/


    /*========== Properties : Service Dependency ==========*/
    public SvcConfig getSvcConfig() {
        return svcConfig;
    }

    public void setSvcConfig(SvcConfig svcConfig) {
        this.svcConfig = svcConfig;
    }

    @JsonProperty("svc")
    private SvcConfig svcConfig;
}