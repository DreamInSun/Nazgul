package cyan.svc;

/**
 * Created by DreamInSun on 2017/11/24.
 */
public class NazException extends Exception {

    /*========== Properties ==========*/
    /**
     * ErrCode 是给程序看的，用来处理报错或者查表装换成用户提示
     */
    protected int errCode = 0;

    /*========== Getter & Setter ==========*/
    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    /*========== Constructor ==========*/
    public NazException(int errCode, String message, Throwable cause) {
        super(message, cause);
        this.errCode = errCode;
    }

    public NazException(int errCode, String message) {
        super(message);
        this.errCode = errCode;
    }
}
