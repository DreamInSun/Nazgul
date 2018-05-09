package cyan.svc.exception;

import org.glassfish.jersey.server.internal.process.MappableException;

/**
 * Created by DreamInSun on 2017/11/24.
 */
public class NazException extends Throwable {

    /*========== Properties ==========*/
    /**
     * ErrCode 是给程序看的，用来处理报错或者查表装换成用户提示
     */
    protected int errCode = 0;

    protected String errInfo = "";

    /*========== Getter & Setter ==========*/
    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

    /*========== Constructor ==========*/
    public NazException(int errCode, String message, Throwable cause) {
        super(message, cause);
        this.errCode = errCode;
        this.errInfo = message;
    }

    public NazException(int errCode, String message) {
        super(message, new Throwable(message));
        this.errCode = errCode;
        this.errInfo = message;
    }
}
