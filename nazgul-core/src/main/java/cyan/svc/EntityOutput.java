package cyan.svc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.*;

/**
 * Created by DreamInSun on 2016/7/7.
 */
public class EntityOutput extends Output {

    /*========== Properties ==========*/
    @JsonInclude(Include.NON_NULL)
    public Object entity;

    /*========== Factory ==========*/
    public static EntityOutput getInstance(int errCode, String errInfo, Object entity) {
        return new EntityOutput(errCode, errInfo, entity);
    }

    public static EntityOutput getInstance(int errCode, IErrInfoMapper infoMapper, Object entity) {
        return new EntityOutput(errCode, infoMapper.getInfo(errCode), entity);
    }

    /*========== Constructor ==========*/
    @JsonCreator
    public EntityOutput(int errCode, String errInfo, Object entity) {
        super(errCode, errInfo);
        this.entity = entity;
    }
}
