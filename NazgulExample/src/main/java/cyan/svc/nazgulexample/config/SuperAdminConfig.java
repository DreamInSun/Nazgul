package cyan.svc.nazgulexample.config;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DreamInSun on 2017/12/26.
 */
public class SuperAdminConfig {

    /*========== Properties ==========*/

    /**
     * 超级管理员用户名
     */
    @JsonProperty
    private String username;

    /**
     * 超级管理员密码
     */
    @JsonProperty
    private String password;

    /*========== Getter & Setter ==========*/

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

    /*========== Constructor ==========*/
    public SuperAdminConfig() {

    }

    public SuperAdminConfig(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
