package cyan.nazgul.dropwizard.config;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DreamInSun on 2016/9/20.
 */
public class ProjectConfig {

    /*========== Properties ==========*/
    @JsonProperty("rootPackage")
    private String rootPackage;

    /*========== Getter @ Setter  ==========*/

    public String getRootPackage() {
        return rootPackage;
    }

    public void setRootPackage(String rootPackage) {
        this.rootPackage = rootPackage;
    }
}
