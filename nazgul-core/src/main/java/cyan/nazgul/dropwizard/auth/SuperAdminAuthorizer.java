package cyan.nazgul.dropwizard.auth;

import cyan.nazgul.dropwizard.BaseConfiguration;
import io.dropwizard.auth.Authorizer;
import jersey.repackaged.com.google.common.collect.Lists;

import java.util.List;

public class SuperAdminAuthorizer implements Authorizer<SuperAdmin> {

    /*========== Properties ==========*/
    BaseConfiguration m_config;

    /*========== Constructor ==========*/
    public SuperAdminAuthorizer(BaseConfiguration config) {
        m_config = config;
    }

    /*========== Interface ; Authorizer ==========*/
    @Override
    public boolean authorize(SuperAdmin superadmin, String role) {
        /*==== STEP 1. From User Find Role =====*/
        List<String> roleList = Lists.newArrayList();
        if (superadmin.getName().endsWith(m_config.getSuperadmin().getName())) {
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