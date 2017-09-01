package cyan.nazgul.dropwizard.auth;

import cyan.nazgul.dropwizard.BaseConfiguration;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;


public class OauthAuthenticator implements Authenticator<BasicCredentials, OauthUser> {

    /*========== Properties ==========*/
    SuperAdmin m_superAdmin;

    /*========== Constructor ==========*/
    public OauthAuthenticator(BaseConfiguration config) {
        m_superAdmin = config.getSuperadmin();
    }

    /*========== Interface : Authenticator ==========*/
    @Override
    public Optional<OauthUser> authenticate(BasicCredentials credentials) throws AuthenticationException {
//        if (checkUser(credentials)) {
//            return Optional.of(new OauthUser(credentials.getUsername()));
//        }
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