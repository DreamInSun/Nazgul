package cyan.svc.nazgul.example.mappers;

import cyan.svc.nazgul.example.entities.User;
import org.apache.ibatis.annotations.Param;

/**
 * Mybatis Demonstration: Mapper
 * Created by DreamInSun on 2016/7/23.
 */
public interface UsersMapper {

    User findById(@Param("id") int id);

    User findByUsername(@Param("name") String name);

    void addUser(@Param("user") User user);
}
