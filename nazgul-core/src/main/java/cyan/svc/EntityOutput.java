package cyan.svc;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.jaxrs.json.annotation.JSONP;
import com.mysql.cj.core.log.LogFactory;
import cyan.svc.err.IErrInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by DreamInSun on 2016/7/7.
 */
public class EntityOutput extends Output {
    private static final Logger g_Logger = LoggerFactory.getLogger(EntityOutput.class);
    /*========== Properties ==========*/
    @JsonProperty
    @JsonInclude(value = Include.NON_NULL)
    private Object entity;

    @JsonProperty
    @JsonInclude(value = Include.NON_NULL)
    private String entityClz;

    @JsonProperty
    @JsonIgnore
    private Integer page;

    @JsonProperty
    @JsonIgnore
    private Integer size;

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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
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

    public static EntityOutput getInstance(NazException ex) {
        return new EntityOutput(ex.getErrCode(), ex.getMessage(), ex.getStackTrace());
    }

    /*========== Constructor ==========*/
    @JsonCreator
    public EntityOutput(){
        super();
        //Default Constructor
    }

    @JsonCreator
    public EntityOutput(int errCode, String errInfo, Object entity) {
        super(errCode, errInfo);
        if (entity != null) {
            this.entityClz = entity.getClass().getSimpleName();
            this.entity = entity;
        }
    }

    @JsonCreator
    public EntityOutput(int errCode, Object entity) {
        super(errCode);
        if (entity != null) {
            this.entity = entity;
            /* Set Additional Information */
            if (entity.getClass().isArray()) {                //如果是Array
                int size = Array.getLength(entity);
                this.setSize(size);
                if (size != 0) {
                    this.setEntityClz(Array.get(entity, 0).getClass().getSimpleName());
                }
            } else if (entity instanceof List) {              //如果是List
                int size = ((List) entity).size();
                this.setSize(size);
                if (size != 0) {
                    this.setEntityClz(((List) entity).get(0).getClass().getSimpleName());
                }
            } else {
                this.entityClz = entity.getClass().getSimpleName();
            }
        }
    }
}
