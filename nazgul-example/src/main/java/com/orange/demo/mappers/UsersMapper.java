package com.orange.demo.mappers;

import com.orange.demo.entities.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by DreamInSun on 2016/7/23.
 */
public interface UsersMapper {

    User findById(@Param("id") int id);

    User findByUsername(@Param("name") String name);

    void addUser(@Param("user") User user);
}
