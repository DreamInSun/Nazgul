package cyan.nazgul.dropwizard.manage;

import io.dropwizard.lifecycle.Managed;

/**
 * Created by DreamInSun on 2016/7/6.
 */
public class RiakClientManager implements Managed {
    private final RiakClient client;

    public RiakClientManager(RiakClient client) {
        this.client = client;
    }

    @Override
    public void start() throws Exception {
        client.start();
    }

    @Override
    public void stop() throws Exception {
        client.stop();
    }
}
