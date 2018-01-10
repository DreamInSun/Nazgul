package cyan.svc.nazgulexample.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import cyan.svc.entity.BaseEntity;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * An Example Entity
 * Created by DreamInSun on 2016/7/22.
 */
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity(name = "naz_person")
public class Person extends BaseEntity {

    /*========== Constructor ==========*/
    public Person() {
    }

    public Person(String personId, String name) {
        this.personId = personId;
        this.name = name;
    }

    /*========== Override ==========*/

    /*========== Properties ==========*/
    /**
     * Person ID
     * Warning ! : If you want to use duplicated primary key, Should not use the ID Generator. Use composit-Ids instead.
     */
//    @Id
//    @Generated(GenerationTime.INSERT)
//    @GeneratedValue(generator = "uuid_gen")
//    @GenericGenerator(name = "uuid_gen", strategy = "uuid")
    @JsonProperty
    @Column(name = "person_id", nullable = false, length = 64, unique = true, insertable = true, updatable = false)
    private String personId;

    @PrePersist
    public void initializeUUID() {
        if (personId == null) {
            personId = UUID.randomUUID().toString();
        }
    }

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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
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
