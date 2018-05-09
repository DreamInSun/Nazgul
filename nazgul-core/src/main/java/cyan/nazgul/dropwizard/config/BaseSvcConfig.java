package cyan.nazgul.dropwizard.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.jaxrs.json.annotation.JSONP;
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

    @JsonProperty("fileStorage")
    protected String  fileStorage;

    /*========== Getter & Setter ==========*/

    public String getBaseDomain() {
        return baseDomain;
    }

    public void setBaseDomain(String baseDomain) {
        this.baseDomain = baseDomain;
    }

    public String getFileStorage() {
        return fileStorage;
    }

    public void setFileStorage(String fileStorage) {
        this.fileStorage = fileStorage;
    }

    /*==========  ==========*/

}
