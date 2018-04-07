package cyan.nazgul.dropwizard.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.javafx.collections.MappingChange;

import java.util.Map;

/**
 * Created by DreamInSun on 2017/11/24.
 */
public class BaseSvcConfig {

    /*========== Properties ==========*/
    /**
     * Base URL of Message Center
     */

    /**
     * 部署之后服务器地址
     */
    @JsonProperty("baseDomain")
    protected String  baseDomain;

    /*========== Getter & Setter ==========*/

    public String getBaseDomain() {
        return baseDomain;
    }

    public void setBaseDomain(String baseDomain) {
        this.baseDomain = baseDomain;
    }

    /*==========  ==========*/

}
