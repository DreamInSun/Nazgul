package cyan.nazgul.servant.entity;

import com.fasterxml.jackson.annotation.*;

/**
 * Created by DreamInSun on 2017/10/8.
 */
public class SvcConfig {

    /*========== Properties ==========*/
    @JsonProperty("SERVICE_NAME")
    protected String svc_name;
    @JsonProperty("SERVICE_VERSION")
    protected String svc_version;
    @JsonProperty("PROFILE")
    protected String profile;
    @JsonProperty("CONFIG_CONN")
    protected String conf_conn;
    @JsonProperty("CONFIG_KEY")
    protected String conf_key;
    @JsonProperty("API_VERSION")
    protected String api_version;
    @JsonIgnore
    protected String groupId;
    @JsonIgnore
    protected String artifactId;

    /*========== Getter & Setter ==========*/
    @JsonGetter("SERVICE_NAME")
    public String getSvc_name() {
        return svc_name.replace(".", "-");
    }

    @JsonSetter("SERVICE_NAME")
    public void setSvc_name(String svc_name) {
        this.svc_name = svc_name.replace("-", ".");
        if (this.svc_name != null) {
            /*===== Consul Compitable =====*/
            String serviceName = svc_name.replace("-", ".");
            /*===== Parse =====*/
            //String[] svcNameSegments = SERVICE_NAME.split("(?=[A-Z])");
            int pos_splitter = serviceName.lastIndexOf(".");
            if (pos_splitter > 0) {
                this.groupId = serviceName.substring(0, pos_splitter);
                this.artifactId = serviceName.substring(pos_splitter + 1);
            }
        }
    }

    @JsonGetter("SERVICE_VERSION")
    public String getSvc_version() {
        return svc_version;
    }

    @JsonSetter("SERVICE_VERSION")
    public void setSvc_version(String svc_version) {
        this.svc_version = svc_version;
    }

    @JsonGetter("PROFILE")
    public String getProfile() {
        return profile;
    }

    @JsonSetter("PROFILE")
    public void setProfile(String profile) {
        this.profile = profile;
    }

    @JsonGetter("CONFIG_CONN")
    public String getConf_conn() {
        return conf_conn;
    }

    @JsonSetter("CONFIG_CONN")
    public void setConf_conn(String conf_conn) {
        this.conf_conn = conf_conn;
    }

    @JsonGetter("CONFIG_KEY")
    public String getConf_key() {
        return conf_key;
    }

    @JsonSetter("CONFIG_KEY")
    public void setConf_key(String conf_key) {
        this.conf_key = conf_key;
    }

    @JsonGetter("API_VERSION")
    public String getApi_version() {
        return api_version;
    }

    @JsonSetter("API_VERSION")
    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    /*========== Assistant ==========*/
    public String getRootPackage() {
        return (this.getGroupId() + '.' + this.getArtifactId()).toLowerCase();
    }
}
