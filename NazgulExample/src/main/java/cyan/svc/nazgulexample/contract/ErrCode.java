package cyan.svc.nazgulexample.contract;

import cyan.svc.err.BaseErrCode;
import cyan.svc.err.ErrInfoMapper;

/**
 * Created by DreamInSun on 2017/9/5.
 */
public class ErrCode extends BaseErrCode{

    public static final int BASE_CODE = 400000;

    /*========== Base ==========*/
    public static final int SUPERADMIN_BASE = BASE_CODE;
    public static final int ROUTE_MAPPING_BASE = BASE_CODE + 1000;

    /*========== BaseErrCode ==========*/

    /*===== Account =====*/
    public static final int SUPERADMIN_APIKEY_NOT_VALID = SUPERADMIN_BASE + 11;

    /*===== Account =====*/

    public static final int ROUTE_MAPPING_SOURCE_IP_ILLEGAL = ROUTE_MAPPING_BASE + 1;
    public static final int ROUTE_MAPPING_SOURCE_PORT_ILLEGAL = ROUTE_MAPPING_BASE + 2;
    public static final int ROUTE_MAPPING_TARGET_HOST_ILLEGAL = ROUTE_MAPPING_BASE + 3;
    public static final int ROUTE_MAPPING_TARGET_PORT_ILLEGAL = ROUTE_MAPPING_BASE + 4;

    public static final int ROUTE_MAPPING_PERSISTENCE_ERROR = ROUTE_MAPPING_BASE + 11;
    public static final int ROUTE_MAPPING_SOURCE_RECORD_NOT_FOUND = ROUTE_MAPPING_BASE + 12;

    /*===== Secure Operation =====*/

    /*=======================================================*/
    /*==================== ErrorInfo Map ====================*/
    /*=======================================================*/
    public static final ErrInfoMapper g_errInfoMapper = (new ErrInfoMapper())
            .put(ErrCode.SUPERADMIN_APIKEY_NOT_VALID, "SUPERADMIN_APIKEY_NOT_VALID")
            .put(ErrCode.ROUTE_MAPPING_PERSISTENCE_ERROR, "ROUTE_MAPPING_PERSISTENCE_ERROR")
            .put(ErrCode.ROUTE_MAPPING_SOURCE_IP_ILLEGAL, "ROUTE_MAPPING_SOURCE_IP_ILLEGAL")
            .put(ErrCode.ROUTE_MAPPING_SOURCE_PORT_ILLEGAL, "ROUTE_MAPPING_SOURCE_PORT_ILLEGAL")
            .put(ErrCode.ROUTE_MAPPING_TARGET_HOST_ILLEGAL, "ROUTE_MAPPING_TARGET_HOST_ILLEGAL")
            .put(ErrCode.ROUTE_MAPPING_TARGET_PORT_ILLEGAL, "ROUTE_MAPPING_TARGET_PORT_ILLEGAL")
            .put(ErrCode.ROUTE_MAPPING_SOURCE_RECORD_NOT_FOUND, "ROUTE_MAPPING_SOURCE_RECORD_NOT_FOUND")
            .put(ErrCode.SUCCESS, "SUCCESS");
}
