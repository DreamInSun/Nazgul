package cyan.nazgul.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import cyan.nazgul.dropwizard.config.BaseSvcConfig;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayConfiguration;
import io.dropwizard.flyway.FlywayFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 带单数据库的基础服务应用配置
 * Created by DreamInSun on 2016/7/21.
 */
public class DbConfiguration<TConfig extends Configuration, TSvcConfig extends BaseSvcConfig> extends BaseConfiguration<TSvcConfig> implements FlywayConfiguration<TConfig> {

    /*========= DataSource ==========*/
    @Valid
    @NotNull
    @JsonProperty("database")
    public DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory() {
        return database;
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
}
