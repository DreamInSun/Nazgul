package cyan.nazgul.dropwizard.auth;

import java.security.Principal;
import java.util.Objects;

/**
 * Created by DreamInSun on 2016/7/14.
 */
public class JwtUser implements Principal {

    /*========= Properties ==========*/
    private final Long id;
    private final String name;

    /*========= Constructor ==========*/
    public JwtUser(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /*========= Properties ==========*/
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final JwtUser myUser = (JwtUser) o;
        return Objects.equals(id, myUser.id) && Objects.equals(name, myUser.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
