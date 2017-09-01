package cyan.nazgul.dropwizard.auth;

import cyan.nazgul.dropwizard.BaseConfiguration;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;


public class SuperAdminAuthenticator implements Authenticator<BasicCredentials, SuperAdmin> {

    /*========== Properties ==========*/
    SuperAdmin m_superAdmin;

    /*========== Constructor ==========*/
    public SuperAdminAuthenticator(BaseConfiguration config) {
        m_superAdmin = config.getSuperadmin();
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
        if (credentials.getPassword().equals(m_superAdmin.getPasswd()) &&
                credentials.getUsername().equals(m_superAdmin.getUsername())) {
            return true;
        } else {
            return false;
        }
    }
}