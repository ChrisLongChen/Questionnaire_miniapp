package com.whu.miniapp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whu.miniapp.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author: 胡龙晨
 * Date: 2021-01-04
 */

@Mapper
public interface UserMapper extends BaseMapper<User>{
    int insertUser(User user);
    User findUserByUserId(int userId);
    User findUserByUsername(String userName);
    User findUserByUserPhone(String userPhone);
    User findUserByWechatid(String openid);
    int updateUserOpenid(User user);
    int updateUserInfo(User user);
    List<User> findUserByAsc();
    List<User> findUserListByUsername(String input);
    List<User> findUserListByUserPhone(String input);
}
