package cyan.nazgul.dropwizard.auth.multiauth;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class MultiAuthSecurityContext<P extends Principal> implements SecurityContext {

  private final MultiAuthPrincipal principal;
  private final boolean       secure;

  public MultiAuthSecurityContext(P principal, boolean secure) {
    this.principal = new MultiAuthPrincipal(principal);
    this.secure    = secure;
  }

  @Override
  public Principal getUserPrincipal() {
    return principal;
  }

  @Override
  public boolean isUserInRole(String role) {
    return false;
  }

  @Override
  public boolean isSecure() {
    return secure;
  }

  @Override
  public String getAuthenticationScheme() {
    return SecurityContext.BASIC_AUTH;
  }
}