package cyan.nazgul.dropwizard.auth.multiauth;

import javax.security.auth.Subject;
import java.security.Principal;

public class MultiAuthPrincipal implements Principal {

  private final Object authenticated;

  public MultiAuthPrincipal(Object authenticated) {
    this.authenticated = authenticated;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public boolean implies(Subject subject) {
    return false;
  }

  public Object getAuthenticated() {
    return authenticated;
  }
}