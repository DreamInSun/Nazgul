package cyan.nazgul.servant.onering.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * [
 * {
 * "key": "database.password",
 * "value": "nazgul",
 * "description": null
 * },
 * {
 * "key": "database.url",
 * "value": "jdbc:mysql://rm-wz9k4pu4q2op9l984eo.mysql.rds.aliyuncs.com:3306/nazgul?characterEncoding=utf8&useSSL=false&autoReconnect=true",
 * "description": null
 * },
 * {
 * "key": "database.user",
 * "value": "nazgul",
 * "description": null
 * }
 * ]
 * Created by DreamInSun on 2018/1/17.
 */
public class ConfigItem {
    /*========== Properties ==========*/
    @JsonProperty
    private String key;

    @JsonProperty
    private String value;

    @JsonProperty
    private String description;

     /*========== Getter & Setter ==========*/
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
