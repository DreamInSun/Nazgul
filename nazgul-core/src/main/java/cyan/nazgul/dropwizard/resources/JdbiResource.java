package cyan.nazgul.dropwizard.resources;

import com.google.common.collect.Maps;
import cyan.nazgul.dropwizard.DbConfiguration;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * DAO definition
 * public interface PersonDAO {
 *
 * @SqlUpdate("CREATE TABLE user ( id INT PRIMARY KEY, name VARCHAR(100) ) IF NOT EXISTS")
 * void createSomethingTable();
 * @SqlUpdate("INSERT INTO user (id, name) VALUES (:id, :name)")
 * void createUser(@Bind("id") int id, @Bind("name") String name);
 * @SqlQuery("SELECT name FROM user WHERE id = :id")
 * String findNameById(@Bind("id") int id);
 * @SqlQuery("SELECT id, name FROM user WHERE id = :id")
 * ResultSet find(@Bind("id") int id);
 * }
 * <p>
 * Usage：
 * # Properties
 * private PersonDAO m_personDao = null;
 * # On Constract
 * this.registerMapper( );
 * m_personDao = (PersonDAO) this.getDAO(PersonDAO.class);
 * # query
 * m_personDao.find(id);
 * <p>
 * Created by DreamInSun on 2016/7/22.
 */
@SuppressWarnings("unchecked")
public class JdbiResource<TConfig extends DbConfiguration> extends BaseResource<TConfig> {

    /*========== Static Properties ==========*/
    protected final static DBIFactory g_DbiFactory = new DBIFactory();
    protected static DBI g_jdbi;

    /*========== Properties ==========*/
    protected Map<Class, Object> m_DaoMap = Maps.newHashMap();

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
