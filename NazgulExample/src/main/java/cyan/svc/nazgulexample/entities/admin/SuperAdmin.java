package cyan.svc.nazgulexample.entities.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import cyan.svc.entity.BaseEntity;

import javax.persistence.Entity;

/**
 * Created by DreamInSun on 2017/12/26.
 */
@Entity(name="naz_super_admin")
public class SuperAdmin extends BaseEntity {

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
    public SuperAdmin() {

    }

    public SuperAdmin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
