package cyan.nazgul.dropwizard.config;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DreamInSun on 2017/11/24.
 */
public class EmailSenderConfig {

    /*========== Properties ==========*/
    /**
     * Base URL of Message Center
     */
    @JsonProperty("isUseSSL")
    protected Boolean isUseSSL;

    /**
     * stmp的域名
     */
    @JsonProperty("stmpHost")
    protected String stmpHost;

    /**
     * stmp的端口号
     */
    @JsonProperty("stmpPort")
    protected String stmpPort;

    /**
     * stmp的端口号
     */
    @JsonProperty("stmpAuth")
    protected String stmpAuth;

    /**
     * stmp的用户名<br/>
     * 注意，某些服务器商是带后缀的，某些服务商是不带后缀的
     */
    @JsonProperty("username")
    protected String username;

    /**
     * stmp的客户端秘钥<br/>
     * 注意，在某些邮件服务商是与登录秘钥不同的独立秘钥
     */
    @JsonProperty("password")
    protected String password;


    /*========== Getter & Setter ==========*/
    public Boolean getUseSSL() {
        return isUseSSL;
    }

    public void setUseSSL(Boolean useSSL) {
        isUseSSL = useSSL;
    }

    public String getStmpHost() {
        return stmpHost;
    }

    public void setStmpHost(String stmpHost) {
        this.stmpHost = stmpHost;
    }

    public String getStmpAuth() {
        return stmpAuth;
    }

    public void setStmpAuth(String stmpAuth) {
        this.stmpAuth = stmpAuth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

