package cyan.nazgul.dropwizard.auth.jwt;

import cyan.nazgul.dropwizard.BaseConfiguration;
import io.dropwizard.auth.Authorizer;

public class JwtUserAuthorizer implements Authorizer<JwtUser> {

    /*========== Properties ==========*/
    private static BaseConfiguration g_config;

    /*========== Constructor ==========*/
    public JwtUserAuthorizer(BaseConfiguration config) {
        g_config = config;
    }

    /*========== Interface ; Authorizer ==========*/
    @Override
    public boolean authorize(JwtUser jwtUser, String role) {
        ///TODO
        role = "JwtUser";
        return true;
    }
}