package cyan.nazgul.dropwizard.auth.superadmin;

import io.dropwizard.auth.AuthValueFactoryProvider;

public class SuperAdminAuthBinder extends AuthValueFactoryProvider.Binder<SuperAdmin> {

    public SuperAdminAuthBinder() {
        super(SuperAdmin.class);
    }
}