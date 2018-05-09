package cyan.nazgul.dropwizard.auth.multiauth;

import io.dropwizard.auth.Auth;
import org.glassfish.jersey.server.model.AnnotatedMethod;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import java.lang.annotation.Annotation;

/**
 * @Override public void run(ExampleConfiguration configuration,
 * Environment environment)
 * {
 * environment.jersey().register(new AuthDynamicFeature(
 * new BasicCredentialAuthFilter.Builder<User>()
 * .setAuthenticator(new UserAuthenticator())
 * .setPrincipal(User.class)
 * .buildAuthFilter(),
 * new BasicCredentialAuthFilter.Builder<Admin>()
 * .setAuthenticator(new AdminAuthenticator())
 * .setPrincipal(Admin.class)
 * .buildAuthFilter()));
 * <p>
 * environment.jersey().register(new AuthValueFactoryProvider.Binder());
 * }
 * @see <a>https://github.com/signalapp/dropwizard-simpleauth</a>
 */
@SuppressWarnings("unchecked")
public class MultiAuthDynamicFeature implements DynamicFeature {

    private MultiAuthFilter[] authFilters;

    public MultiAuthDynamicFeature(MultiAuthFilter... authFilters) {
        this.authFilters = authFilters;
    }


    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        AnnotatedMethod annotatedMethod = new AnnotatedMethod(resourceInfo.getResourceMethod());
        Annotation[][] parameterAnnotations = annotatedMethod.getParameterAnnotations();
        Class<?>[] parameterTypes = annotatedMethod.getParameterTypes();

        verifyAuthAnnotations(parameterAnnotations);

        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (Annotation annotation : parameterAnnotations[i]) {
                if (annotation instanceof Auth) {
                    context.register(getFilterFor(parameterTypes[i]));
                }
            }
        }
    }

    private MultiAuthFilter getFilterFor(Class<?> parameterType) {
        for (MultiAuthFilter filter : authFilters) {

            if (filter.supports(parameterType))
                return filter;
        }

        throw new IllegalArgumentException("No authenticator for type: " + parameterType);
    }

    private void verifyAuthAnnotations(Annotation[][] parameterAnnotations) {
        int authCount = 0;

        for (Annotation[] annotations : parameterAnnotations) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof Auth) authCount++;
            }
        }

        if (authCount > 1) {
            throw new IllegalArgumentException("Only one @Auth tag supported per resource method!");
        }
    }

}