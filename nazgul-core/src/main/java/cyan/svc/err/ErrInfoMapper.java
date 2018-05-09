package cyan.svc.err;

import java.util.Map;

/**
 * This is
 * Created by DreamInSun on 2017/10/24.
 */
public class ErrInfoMapper implements IErrInfoMapper {
    /*========== Static Properties ==========*/
    private Map<Integer, String> g_errInfoMap = new java.util.HashMap<>();

    /*========== Constructor ==========*/
    public ErrInfoMapper() {
        //Output.setErrInfoMapping(this);
    }

    /*========== Add Record ==========*/
    public ErrInfoMapper put(int errCode, String errInfo) {
        g_errInfoMap.put(errCode, errInfo);
        return this;
    }

    /*========== Export Function ==========*/
    @Override
    public String getInfo(int errCode) {
        return g_errInfoMap.get(errCode);
    }


}
