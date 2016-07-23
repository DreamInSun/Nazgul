package cyan.nazgul.dropwizard.auth;

import javax.security.auth.Subject;
import java.security.Principal;

/**
 * Created by DreamInSun on 2016/7/14.
 */
public class User implements Principal {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }

    public User(String user) {

    }
}
