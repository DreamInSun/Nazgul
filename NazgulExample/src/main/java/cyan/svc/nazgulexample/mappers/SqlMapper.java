package cyan.svc.nazgulexample.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by DreamInSun on 2017/12/26.
 */
public interface SqlMapper {
    List<Map> executeSql(@Param(value = "sqlStr") String sql);

    Integer heartBeat();
}
