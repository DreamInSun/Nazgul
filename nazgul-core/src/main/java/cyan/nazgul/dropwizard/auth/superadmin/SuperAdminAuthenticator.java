package cyan.nazgul.dropwizard.auth.superadmin;

import cyan.nazgul.dropwizard.BaseConfiguration;
import cyan.nazgul.dropwizard.container.GlobalInstance;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;


public class SuperAdminAuthenticator implements Authenticator<BasicCredentials, SuperAdmin> {

    /*========== Properties ==========*/
    private static SuperAdmin g_superAdmin;

    /*========== Constructor ==========*/
    public SuperAdminAuthenticator(BaseConfiguration config) {
        g_superAdmin = config.getSuperadmin();
    }

    /*========== Interface : Authenticator ==========*/
    @Override
    public Optional<SuperAdmin> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if (checkUser(credentials)) {
            return Optional.of(new SuperAdmin(credentials.getUsername(), credentials.getPassword()));
        }
        return Optional.empty();
    }

    protected Boolean checkUser(BasicCredentials credentials) {
        if (g_superAdmin == null) {
            g_superAdmin = GlobalInstance.getConfiguration().getSuperadmin();
        }

        if (credentials.getPassword().equals(g_superAdmin.getPasswd()) &&
                credentials.getUsername().equals(g_superAdmin.getUsername())) {
            return true;
        } else {
            return false;
        }
    }
}