package cyan.svc;

/**
 * Created by DreamInSun on 2016/7/7.
 */
public class EntityOutput extends Output {

    /*========== Properties ==========*/
    public Object entity;

    /*========== Constructor ==========*/
    public EntityOutput(int errCode, String errInfo, Object entity) {
        super(errCode, errInfo);
        this.entity = entity;
    }
}
