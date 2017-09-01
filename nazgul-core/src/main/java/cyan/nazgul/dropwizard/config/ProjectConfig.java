package cyan.nazgul.dropwizard.config;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DreamInSun on 2016/9/20.
 */
public class ProjectConfig {

    @JsonProperty("rootPackage")
    public String rootPackage;

}
