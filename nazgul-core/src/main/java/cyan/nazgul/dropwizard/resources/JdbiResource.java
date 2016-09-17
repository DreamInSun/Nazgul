package cyan.nazgul.dropwizard.resources;

import cyan.nazgul.dropwizard.DbConfiguration;
import io.dropwizard.Configuration;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DreamInSun on 2016/7/22.
 */
public class JdbiResource<TConfig extends DbConfiguration> extends BaseResource<TConfig> {

    /*========== Static Properties ==========*/
    protected final static DBIFactory g_DbiFactory = new DBIFactory();
    protected static DBI g_jdbi;

    /*========== Properties ==========*/
    protected Map<Class, Object> m_DaoMap = new HashMap<>();

    /*========== Constructor ==========*/
    public JdbiResource(TConfig config, Environment environment) {
        super(config, environment);
        if (g_jdbi == null) {
            g_jdbi = g_DbiFactory.build(environment, config.getDataSourceFactory(), "mysql");
        }
    }

    public void registerMapper(ResultSetMapper mapper) {
        g_jdbi.registerMapper(mapper);
    }

    public Object getDAO(Class klass) {
        Object retObj = m_DaoMap.get(klass);
        if (retObj == null) {
            retObj = g_jdbi.onDemand(klass);
            m_DaoMap.put(klass, retObj);
        }
        return retObj;
    }
}
