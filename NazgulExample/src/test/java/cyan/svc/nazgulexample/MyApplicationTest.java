package cyan.svc.nazgulexample;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cyan.svc.nazgulexample.config.SuperAdminConfig;
import org.junit.Before;
import org.junit.Test;

import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Environment;

/**
 * JUnit Test
 * Created by DreamInSun on 2016/7/6.
 */
public class MyApplicationTest {
    String[] args = {"docker"};
    private final Environment environment = mock(Environment.class);
    private final JerseyEnvironment jersey = mock(JerseyEnvironment.class);
    private final Application application = new Application(args);
    private final Configuration config = new Configuration();

    @Before
    public void setup() throws Exception {
        config.setSuperAdminConfig(new SuperAdminConfig());
        when(environment.jersey()).thenReturn(jersey);

    }

    @Test
    public void buildsAThingResource() throws Exception {
//        application.run(config, environment);
//        verify(jersey).register(isA(HelloWorldResource.class));
    }
}
