package cyan.nazgul.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import cyan.nazgul.dropwizard.config.BaseSvcConfig;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayConfiguration;
import io.dropwizard.flyway.FlywayFactory;
import org.apache.ibatis.session.SqlSession;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 带单数据库的基础服务应用配置
 * Created by DreamInSun on 2016/7/21.
 */
public class DbConfiguration<TConfig extends Configuration, TSvcConfig extends BaseSvcConfig> extends BaseConfiguration<TSvcConfig>
        implements FlywayConfiguration<TConfig> {

    /*========= DataSource ==========*/
    @Valid
    @NotNull
    @JsonProperty("database")
    protected DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDatabase() {
        return database;
    }

    public void setDatabase(DataSourceFactory database) {
        this.database = database;
    }

    /* Assistant */
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }



    /*========= DataSources Map ==========*/
    @JsonProperty("databaseMap")
    protected Map<String, DataSourceFactory> databaseMap;

    public Map<String, DataSourceFactory> getDatabaseMap() {
        return databaseMap;
    }

    public void setDatabaseMap(Map<String, DataSourceFactory> databaseMap) {
        this.databaseMap = databaseMap;
    }

     /* Assisatnt Functino */
    /**
     * 获取数据库Map中的连接
     *
     * @param name
     * @return
     */
    public DataSourceFactory getDataSourceFactory(String name) {
        DataSourceFactory dataSourceFactory = null;
        if (databaseMap != null) {
            dataSourceFactory = databaseMap.get(name);
        }
        /* 在输入参数不明确的情况下传入默认参数 */
        if (name == null && dataSourceFactory == null) {
            return database;
        }
        return dataSourceFactory;
    }

    /*========= Flyway ==========*/
    @Valid
    @NotNull
    @JsonProperty("flyway")
    public FlywayFactory flywayFactory = new FlywayFactory();

    @Override
    public FlywayFactory getFlywayFactory(TConfig configuration) {
        return flywayFactory;
    }

    public FlywayFactory getFlywayFactory() {
        return flywayFactory;
    }

    public void setFlywayFactory(FlywayFactory flywayFactory) {
        this.flywayFactory = flywayFactory;
    }
}
