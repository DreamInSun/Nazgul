package cyan.nazgul.dropwizard.auth;

import io.dropwizard.auth.Authorizer;

/**
 * Created by DreamInSun on 2016/7/14.
 */
public class ExampleAuthorizer implements Authorizer<User> {
    @Override
    public boolean authorize(User user, String role) {
        return user.getName().equals("good-guy") && role.equals("ADMIN");
    }
}
