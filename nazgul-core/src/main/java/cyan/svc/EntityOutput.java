package cyan.svc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import cyan.svc.err.IErrInfoMapper;

/**
 * Created by DreamInSun on 2016/7/7.
 */
public class EntityOutput extends Output {

    /*========== Properties ==========*/
    @JsonProperty
    @JsonInclude(value = Include.NON_NULL)
    private Object entity;

    @JsonProperty
    @JsonInclude(value = Include.NON_NULL)
    private String entityClz;

    /*========== Getter & Setter ==========*/
    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public String getEntityClz() {
        return entityClz;
    }

    public void setEntityClz(String entityClz) {
        this.entityClz = entityClz;
    }

    /*========== Factory ==========*/

    /* Customize Error Info Map */
    public static EntityOutput getInstance(int errCode, String errInfo, Object entity) {
        return new EntityOutput(errCode, errInfo, entity);
    }

    public static EntityOutput getInstance(int errCode, IErrInfoMapper infoMapper, Object entity) {
        return new EntityOutput(errCode, infoMapper.getInfo(errCode), entity);
    }

    /* Quick Return */
    public static EntityOutput getInstance(int errCode) {
        return new EntityOutput(errCode, null);
    }

    public static EntityOutput getInstance(int errCode, Object entity) {
        return new EntityOutput(errCode, entity);
    }

    /*========== Constructor ==========*/
    @JsonCreator
    public EntityOutput(int errCode, String errInfo, Object entity) {
        super(errCode, errInfo);
        this.entityClz = entity.getClass().getSimpleName();
        this.entity = entity;
    }

    @JsonCreator
    public EntityOutput(int errCode, Object entity) {
        super(errCode);
        if (entity != null) {
            this.entityClz = entity.getClass().getSimpleName();
            this.entity = entity;
        }
    }
}
