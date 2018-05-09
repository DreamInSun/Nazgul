package cyan.svc.output;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DreamInSun on 2016/7/7.
 */
public class EntitiesOutput extends Output {

    /*========== Properties ==========*/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Object[] entities;

    @JsonProperty
    public Integer index;

    @JsonProperty
    public Integer size;

    /*========== Factory ==========*/
    public static EntitiesOutput getInstance(int errCode, String errInfo, Object entity) {
        return new EntitiesOutput(errCode, errInfo, entity);
    }

    /*========== Constructor ==========*/
    @JsonCreator
    public EntitiesOutput(int errCode, String errInfo, Object entity) {
        super(errCode, errInfo);
        this.entities = entities;
    }


}
