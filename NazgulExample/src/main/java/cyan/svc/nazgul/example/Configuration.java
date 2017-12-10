package cyan.svc.nazgul.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import cyan.nazgul.dropwizard.DbConfiguration;
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
    @NotEmpty
    @JsonProperty("template")
    private String template;

    @NotEmpty
    @JsonProperty("defaultName")
    private String defaultName = "Friend";

    @JsonProperty("template")
    public String getTemplate() {
        return template;
    }

    @JsonProperty("template")
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty("defaultName")
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty("defaultName")
    public void setDefaultName(String name) {
        this.defaultName = name;
    }

    /*========== Implements : Jobs ==========*/
    public Map<String, String> getJobs() {
        return Collections.emptyMap();
    }
}