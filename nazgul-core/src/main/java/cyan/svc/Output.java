package cyan.svc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DreamInSun on 2016/7/7.
 */
public class Output {

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
    public Output(int errCode, String errInfo) {
        this.errCode = errCode;
        this.errInfo = errInfo;
    }
}
