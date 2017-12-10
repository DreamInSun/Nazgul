package cyan.nazgul.dropwizard.config;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DreamInSun on 2017/12/1.
 */
public class CrossdomainConfig {

    /*========== properties ==========*/
    @JsonProperty
    private Boolean enable;

    @JsonProperty
    private String accessControlAllowOrigin;

    @JsonProperty
    private String accessControlAllowHeaders;

    @JsonProperty
    private String accessControlAllowCredentials;

    @JsonProperty
    private String accessControlAllowMethods;

    @JsonProperty
    private String accessControlMaxAge;

    /*========== Getter & Setter ==========*/

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getAccessControlAllowOrigin() {
        return accessControlAllowOrigin;
    }

    public void setAccessControlAllowOrigin(String accessControlAllowOrigin) {
        this.accessControlAllowOrigin = accessControlAllowOrigin;
    }

    public String getAccessControlAllowHeaders() {
        return accessControlAllowHeaders;
    }

    public void setAccessControlAllowHeaders(String accessControlAllowHeaders) {
        this.accessControlAllowHeaders = accessControlAllowHeaders;
    }

    public String getAccessControlAllowCredentials() {
        return accessControlAllowCredentials;
    }

    public void setAccessControlAllowCredentials(String accessControlAllowCredentials) {
        this.accessControlAllowCredentials = accessControlAllowCredentials;
    }

    public String getAccessControlAllowMethods() {
        return accessControlAllowMethods;
    }

    public void setAccessControlAllowMethods(String accessControlAllowMethods) {
        this.accessControlAllowMethods = accessControlAllowMethods;
    }

    public String getAccessControlMaxAge() {
        return accessControlMaxAge;
    }

    public void setAccessControlMaxAge(String accessControlMaxAge) {
        this.accessControlMaxAge = accessControlMaxAge;
    }
}
