package cyan.nazgul.servant.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DreamInSun on 2017/10/8.
 */
public class FileMapping {

    /*========== Constructor ==========*/
    @JsonCreator
    public FileMapping() {

    }

    /*========== Properties ==========*/
    @JsonProperty("packageBase")
    private String packageBase;

    @JsonProperty("dockerfile")
    private String dockerfile;

    @JsonProperty("mavenPom")
    private String mavenPom;

    @JsonProperty("dockerEnv")
    private String dockerEnv;

    @JsonProperty("defaultConf")
    private String defaultConf;

    /*========== Getter & Setter ==========*/
    @JsonAnyGetter
    public String getPackageBase() {
        return packageBase;
    }

    @JsonAnySetter
    public void setPackageBase(String packageBase) {
        this.packageBase = packageBase;
    }

    @JsonAnyGetter
    public String getDockerfile() {
        return dockerfile;
    }

    @JsonAnySetter
    public void setDockerfile(String dockerfile) {
        this.dockerfile = dockerfile;
    }

    @JsonAnyGetter
    public String getMavenPom() {
        return mavenPom;
    }

    @JsonAnySetter
    public void setMavenPom(String mavenPom) {
        this.mavenPom = mavenPom;
    }

    @JsonAnyGetter
    public String getDockerEnv() {
        return dockerEnv;
    }

    @JsonAnySetter
    public void setDockerEnv(String dockerEnv) {
        this.dockerEnv = dockerEnv;
    }

    @JsonAnyGetter
    public String getDefaultConf() {
        return defaultConf;
    }

    @JsonAnySetter
    public void setDefaultConf(String defaultConf) {
        this.defaultConf = defaultConf;
    }
}
