package com.whu.miniapp.mapper;

import com.whu.miniapp.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author: 胡龙晨
 * Date: 2021-01-04
 */

@Mapper
public interface UserMapper {
    int insertUser(User user);
    User findUserByUsername(String user_name);
    User findUserByUserPhone(String user_phone);
}
