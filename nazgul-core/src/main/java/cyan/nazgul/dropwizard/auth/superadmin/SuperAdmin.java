package cyan.nazgul.dropwizard.auth.superadmin;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.security.Principal;

/**
 * SuperAdmin 定义为系统部署时配置的管理员，具有最高的系统管理权限，其验证来源非任何外源数据配置
 * Created by DreamInSun on 2016/9/19.
 */
public class SuperAdmin implements Principal {
    /*========== Properties ==========*/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty
    @JsonProperty("username")
    private String username;

    /*========== Properties ==========*/
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @NotEmpty
    @JsonProperty
    private String passwd;

    /*========== Interface : Principal ==========*/
    @Override
    public String getName() {
        return this.username;
    }

    /*========== Constructor ==========*/
    public SuperAdmin() {

    }

    public SuperAdmin(String username, String passwd) {
        this.username = username;
        this.passwd = passwd;
    }
}
