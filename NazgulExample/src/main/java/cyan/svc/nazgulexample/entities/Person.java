package cyan.svc.nazgulexample.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import cyan.svc.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * An Example Entity
 * Created by DreamInSun on 2016/7/22.
 */
@Entity(name="naz_person")
public class Person extends BaseEntity {

    /*========== Constructor ==========*/
    public Person() {

    }

    public Person(int pid, String name) {
        this.pid = pid;
        this.name = name;
    }

    /*========== Properties ==========*/
    /**
     * Person ID
     */
    @NotNull
    @JsonProperty
    @Column(name = "pid", nullable = false, length = 64, unique = true, updatable = false)
    private Integer pid;

    /**
     * Person Name
     */
    @JsonProperty
    @Column(name = "name", nullable = true, length = 64, unique = false, updatable = true)
    private String name;

    /**
     * 年龄
     */
    @JsonProperty
    @Column(name = "age", nullable = true, length = 11, unique = false, updatable = true)
    private Integer age;

    /**
     * 性别 0-男 1-女
     */
    @JsonProperty
    @Column(name = "gender", nullable = true, length = 3, unique = false, updatable = true)
    private Integer gender;

    /**
     * 名
     */
    @JsonProperty
    @Column(name = "first_name", nullable = true, length = 64, unique = false, updatable = true)
    private String firstName;

    /**
     * 姓
     */
    @JsonProperty
    @Column(name = "last_name", nullable = true, length = 64, unique = false, updatable = true)
    private String lastName;

    /*========== Getter & Setter ==========*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
