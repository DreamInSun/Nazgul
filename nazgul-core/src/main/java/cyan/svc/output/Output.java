package cyan.svc.output;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cyan.svc.err.IErrInfoMapper;

/**
 * Created by DreamInSun on 2016/7/7.
 */
public class Output {

    /*========== Static Properties ==========*/
    protected static IErrInfoMapper g_ErrInfoMapper;

    /*========== Properties ==========*/
    /**
     * ErrCode 是给程序看的，用来处理报错或者查表装换成用户提示
     */
    protected int errCode = 0;
    /**
     * ErrInfo 是给人看的，用来调试错误,不应该直接显示给用户
     */
    protected String errInfo;


    /*========== Getter & Setter ==========*/
    @JsonProperty
    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    @JsonProperty
    public String getErrInfo() {
        return errInfo;
    }

    @JsonProperty
    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

    @JsonProperty
    public int getErrCode() {
        return errCode;
    }


    /*========== Constructor ==========*/
    @JsonCreator
    public Output() {
        //Default Constructor
    }

    @JsonCreator
    public Output(int errCode, String errInfo) {
        this.errCode = errCode;
        this.errInfo = errInfo;
    }

    @JsonCreator
    public Output(int errCode) {
        this.errCode = errCode;
        if (null != g_ErrInfoMapper) {
            this.errInfo = g_ErrInfoMapper.getInfo(errCode);
        }
    }

    public static void setErrInfoMapping(IErrInfoMapper errInfoMapper) {
        g_ErrInfoMapper = errInfoMapper;
    }

    public static IErrInfoMapper getErrInfoMapping() {
        return g_ErrInfoMapper;
    }
}
