package cyan.nazgul.dropwizard.auth.jwt;

import io.dropwizard.auth.AuthValueFactoryProvider;

public class JwtAuthBinder extends AuthValueFactoryProvider.Binder<JwtUser> {

    public JwtAuthBinder() {
        super(JwtUser.class);
    }
}