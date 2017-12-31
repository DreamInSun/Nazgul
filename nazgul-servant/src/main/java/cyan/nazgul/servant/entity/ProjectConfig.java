package cyan.nazgul.servant.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DreamInSun on 2017/12/25.
 */
public class ProjectConfig {

    /*========== Properties ==========*/
    @JsonProperty("rootPackage")
    private String rootPackage;

    /*========== Getter & Setter ==========*/
    public String getRootPackage() {
        return rootPackage;
    }

    public void setRootPackage(String rootPackage) {
        this.rootPackage = rootPackage;
    }
}
