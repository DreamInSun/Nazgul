package cyan.nazgul.dropwizard.auth.multiauth;

import com.google.common.base.Preconditions;
import io.dropwizard.auth.*;
import io.dropwizard.auth.Authenticator;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestFilter;
import java.security.Principal;

@Priority(Priorities.AUTHENTICATION)
public abstract class MultiAuthFilter<C, P extends Principal> extends AuthFilter<C, P> implements ContainerRequestFilter {

    /*========== Properties ==========*/
    protected Class<P> principalType;

    public boolean supports(Class<?> clazz) {
        return clazz.equals(principalType);
    }

    /**
     * Abstract builder for auth filters.
     *
     * @param <C> the type of credentials that the filter accepts
     * @param <P> the type of the principal that the filter accepts
     */
    public abstract static class AuthFilterBuilder<C, P extends Principal, T extends MultiAuthFilter<C, P>>{

        private String realm = "realm";
        private String prefix = "Basic";
        private Authenticator<C, P> authenticator;
        private Authorizer<P> authorizer = new PermitAllAuthorizer<>();
        private Class<P> principalType;

        private UnauthorizedHandler unauthorizedHandler = new DefaultUnauthorizedHandler();

        /**
         * Sets the given realm
         *
         * @param realm a realm
         * @return the current builder
         */
        public AuthFilterBuilder<C, P, T> setRealm(String realm) {
            this.realm = realm;
            return this;
        }

        /**
         * Sets the given prefix
         *
         * @param prefix a prefix
         * @return the current builder
         */
        public AuthFilterBuilder<C, P, T> setPrefix(String prefix) {
            this.prefix = prefix;
            return this;
        }

        /**
         * Sets the given authenticator
         *
         * @param authenticator an {@link io.dropwizard.auth.Authenticator}
         * @return the current builder
         */
        public AuthFilterBuilder<C, P, T> setAuthenticator(Authenticator<C, P> authenticator) {
            this.authenticator = authenticator;
            return this;
        }

        public AuthFilterBuilder<C, P, T> setPrincipal(Class<P> principalType) {
            this.principalType = principalType;
            return this;
        }

        public AuthFilterBuilder<C, P, T> setAuthorizer(Authorizer<P> authorizer) {
            this.authorizer = authorizer;
            return this;
        }


        /**
         * Builds an instance of the filter with a provided authenticator,
         * an authorizer, a prefix, and a realm.
         *
         * @return a new instance of the filter
         */
        public T buildAuthFilter() {
            Preconditions.checkNotNull(realm, "Realm is not set");
            Preconditions.checkNotNull(prefix, "Prefix is not set");
            Preconditions.checkNotNull(authenticator, "Authenticator is not set");
            Preconditions.checkNotNull(authorizer, "Authorizer is not set");
            Preconditions.checkNotNull(principalType, "principalType is not set");
            Preconditions.checkNotNull(unauthorizedHandler, "Unauthorized handler is not set");

            final T authFilter = newInstance();
            authFilter.authorizer = authorizer;
            authFilter.authenticator = authenticator;
            authFilter.prefix = prefix;
            authFilter.realm = realm;
            authFilter.principalType = principalType;
            authFilter.unauthorizedHandler = unauthorizedHandler;
            return authFilter;
        }

        protected abstract T newInstance();
    }

}