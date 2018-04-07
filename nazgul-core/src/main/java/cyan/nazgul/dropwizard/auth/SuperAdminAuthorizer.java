package cyan.nazgul.dropwizard.auth;

import cyan.nazgul.dropwizard.BaseConfiguration;
import cyan.nazgul.dropwizard.container.GlobalInstance;
import io.dropwizard.auth.Authorizer;
import jersey.repackaged.com.google.common.collect.Lists;

import java.util.List;

public class SuperAdminAuthorizer implements Authorizer<SuperAdmin> {

    /*========== Properties ==========*/
    private static BaseConfiguration g_config;

    /*========== Constructor ==========*/
    public SuperAdminAuthorizer(BaseConfiguration config) {
        g_config = config;
    }

    /*========== Interface ; Authorizer ==========*/
    @Override
    public boolean authorize(SuperAdmin superadmin, String role) {
        if (g_config == null) {
            g_config = GlobalInstance.getConfiguration();
        }
        /*==== STEP 1. From User Find Role =====*/
        List<String> roleList = Lists.newArrayList();
        if (superadmin.getName().endsWith(g_config.getSuperadmin().getName())) {
            roleList.add("SUPER_ADMIN");
        }
        /*===== STEP 2. Check Roles =====*/
        for (String userRole : roleList) {
            if (userRole.equals(role)) {
                return true;
            }
        }
        return false;
    }
}