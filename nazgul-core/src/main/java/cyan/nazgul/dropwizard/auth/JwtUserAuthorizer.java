package cyan.nazgul.dropwizard.auth;

import cyan.nazgul.dropwizard.BaseConfiguration;
import cyan.nazgul.dropwizard.container.GlobalInstance;
import io.dropwizard.auth.Authorizer;
import jersey.repackaged.com.google.common.collect.Lists;

import java.util.List;

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