package cyan.svc.nazgulexample.entities.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import cyan.svc.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by DreamInSun on 2017/12/26.
 */
@Entity(name = "naz_super_admin")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class SuperAdmin extends BaseEntity {

    /*========== Properties ==========*/

    /**
     * 超级管理员用户名
     */
    @JsonProperty
    @Column(name = "username", nullable = false, length = 64, unique = true, updatable = false)
    private String username;

    /**
     * 超级管理员密码
     */
    @JsonProperty
    @Column(name = "password", nullable = true, length = 64, unique = false, updatable = true)
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
