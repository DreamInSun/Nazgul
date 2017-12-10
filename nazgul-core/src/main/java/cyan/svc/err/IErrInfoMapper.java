package cyan.svc.err;

/**
 * Created by DreamInSun on 2017/9/11.
 */
public interface IErrInfoMapper {
    /**
     * @param errCode
     * @return
     */
    String getInfo(int errCode);
}
