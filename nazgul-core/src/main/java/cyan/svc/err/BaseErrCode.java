package cyan.svc.err;

/**
 * Created by DreamInSun on 2018/1/1.
 */
public class BaseErrCode {
    /*========== Default ===========*/
    public static final int SUCCESS = 0;

    /*========== Entity ===========*/
    public static final int ENTITY_BASE = 10000;
    public static final int ENTITY_CLASS_NOT_FOUND = ENTITY_BASE + 1;
    public static final int ENTITY_CLASS_JPA_ERROR = ENTITY_BASE + 2;

    public static final int ENTITY_QUERY_STRING_ERROR = ENTITY_BASE + 3;
    public static final int ENTITY_CLASS_REFLECTION_ERROR = ENTITY_BASE + 4;

    public static final int ENTITY_QUERY_PERSISTENCE_ERROR = ENTITY_BASE + 5;
    public static final int ENTITY_DIFINITION_ERROR = ENTITY_BASE + 6;
}
