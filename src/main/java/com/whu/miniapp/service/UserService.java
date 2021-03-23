package com.whu.miniapp.service;

import com.whu.miniapp.entity.User;
import com.whu.miniapp.mapper.UserMapper;
import com.whu.miniapp.util.JwtUtil;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

/**
 * Author: 胡龙晨
 * Date: 2021-01-04
 */

@Service
public class UserService {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    protected JwtUtil jwtUtil;


    /**
     * 获取用户信息
     *
     * @param userId
     * @return Response
     */
    @Transactional
    public User getUserInfo(Integer userId){
        User user = userMapper.findUserByUserId(userId);
        return user;
    }

    /**
     * 修改用户信息
     *
     * @param userId
     * @param userName
     * @param userGender
     * @param userAge
     * @param userCareer
     * @param userAddr
     * @param introduction
     * @return Response
     */
    @Transactional
    public int modifyUserInfo(Integer userId, @Nullable String userName,@Nullable String userGender,@Nullable Integer userAge,@Nullable String userCareer,@Nullable String userAddr,@Nullable String introduction){
        User user = userMapper.findUserByUserId(userId);
        user.setUserName(userName);
        user.setUserGender(userGender);
        user.setUserAge(userAge);
        user.setUserCareer(userCareer);
        user.setUserAddr(userAddr);
        user.setIntroduction(introduction);
        int cnt = userMapper.updateUserInfo(user);
        if(cnt >= 1){
            return 0;
        }
        else{
            return -1;
        }
    }

}
