package cyan.svc.err;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * Created by DreamInSun on 2018/1/1.
 */
public class BaseErrCode {
    public static final Logger g_logger = LoggerFactory.getLogger(BaseErrCode.class);

    /*========== Default ===========*/
    public static final int SUCCESS = 0;

    /*========== Framework Internal ===========*/
    public static final int NAZ_INTERNAL_UNHANDLERED_ERR = 100;

    /*========== Entity ===========*/
    public static final int ENTITY_BASE = 10000;
    public static final int ENTITY_CLASS_NOT_FOUND = ENTITY_BASE + 1;
    public static final int ENTITY_CLASS_JPA_ERROR = ENTITY_BASE + 2;

    public static final int ENTITY_QUERY_STRING_ERROR = ENTITY_BASE + 3;
    public static final int ENTITY_CLASS_REFLECTION_ERROR = ENTITY_BASE + 4;

    public static final int ENTITY_QUERY_PERSISTENCE_ERROR = ENTITY_BASE + 5;
    public static final int ENTITY_DIFINITION_ERROR = ENTITY_BASE + 6;

    /*========== Input ===========*/
    public static final int INPUT_BASE = 20000;
    public static final int INPUT_ERQUIRED_PARAM_IS_NULL = INPUT_BASE + 1;
    public static final int INPUT_PARAM_FORMAT_IS_ILLEGAL = INPUT_BASE + 2;

    public static final int INPUT_DATABASE_QUERY_ERROR = INPUT_BASE + 21;

    public static final int INPUT_PARAM_IS_NOT_VALID = INPUT_BASE + 31;
    public static final int INPUT_PARAM_INSUFFICENT = INPUT_BASE + 32;

    /*========== Config ===========*/
    public static final int CONFIG_BASE = 30000;
    public static final int CONFIG_REQUIRED_CONFIG_IS_NULL = CONFIG_BASE + 1;
    public static final int CONFIG_VALUE_NOT_VALID = CONFIG_BASE + 11;

    /*========== SYSTEM_IO ===========*/
    public static final int SYSTEM_IO_BASE = 40000;

    /*========== SVC ===========*/
    public static final int SVC_BASE = 50000;


    /*===== Superadmin =====*/
    public static final int SUPERADMIN_BASE = 60000;
    public static final int SUPERADMIN_APIKEY_NOT_VALID = SUPERADMIN_BASE + 11;


    /*========== Assistant Function ==========*/
    public static ErrInfoMapper getDefaultErrInfoMapper(Class<? extends BaseErrCode> errCodeClz) {
        ErrInfoMapper errInfoMapper = new ErrInfoMapper();
        try {
            Field[] fieldParent = BaseErrCode.class.getDeclaredFields();

            Class clazz = errCodeClz;//根据类名获得其对应的Class对象 写上你想要的类名就是了 注意是全名 如果有包的话要加上 比如java.Lang.String
            Field[] childFelds = clazz.getDeclaredFields();//根据Class对象获得属性 私有的也可以获得
            /* */
            Field[] fields = concat(fieldParent, childFelds);
            for (Field f : fields) {
                if (f.getType().equals(int.class)) {
                    int errCode = (int) f.get(null);
                    String errInfo = f.getName();
                    g_logger.debug("注册错误代码：" + errInfo + ":" + errCode);//打印每个属性的类型名字
                    String oldErrInfo = errInfoMapper.getInfo(errCode);
                    if (null != oldErrInfo) {
                        g_logger.error("错误代码 “" + errInfo + "”与“" + oldErrInfo + "”重复");
                    } else {
                        errInfoMapper.put(errCode, errInfo);
                    }
                }
            }
        } catch (Exception e) {
            g_logger.error(e.getMessage());
        }
        return errInfoMapper;
    }

    static Field[] concat(Field[] a, Field[] b) {
        Field[] c = new Field[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

}
