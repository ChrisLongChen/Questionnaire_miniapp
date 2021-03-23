package com.whu.miniapp.service;

import com.auth0.jwt.JWT;
import com.whu.miniapp.entity.User;
import com.whu.miniapp.mapper.UserMapper;
import com.whu.miniapp.util.JwtUtil;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

/**
 * Author: 胡龙晨
 * Date: 2021-01-04
 */

@Service
public class WxService {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    protected JwtUtil jwtUtil;

    /**
     * 用户注册
     *
     * @param userName
     * @param userPasscode
     * @param userGender
     * @param userAge
     * @param userCareer
     * @param userPhone
     * @param userAddr
     * @return Response
     */

    @Transactional
    public int userRegister(String userName,String userPasscode,String userGender,Integer userAge,String userCareer,String userPhone,String userAddr,String introduction){
        User user1 = userMapper.findUserByUsername(userName);
        if (user1 != null){
            return -1;
        }
        User user2 = userMapper.findUserByUserPhone(userPhone);
        if (user2 != null){
            return -2;
        }
        User user = new User();
        user.setUserName(userName);
        user.setUserPasscode(userPasscode);
        user.setUserGender(userGender);
        user.setUserAge(userAge);
        user.setUserCareer(userCareer);
        user.setUserPhone(userPhone);
        user.setUserAddr(userAddr);
        user.setIntroduction(introduction);
        user.setUserStatus(0);
        user.setPoint(0);
        int cnt = userMapper.insertUser(user);
        if(cnt > 0 ){
            return 0;
        }
        else{
            return -3;
        }
    }


    /**
     * 用户登录
     *
     * @param openid
     * @param input
     * @param userPasscode
     * @return Response
     */
    @Transactional
    public int userLogin(String openid,String input,String userPasscode){
        User user = userMapper.findUserByWechatid(openid);
        User user1 = userMapper.findUserByUsername(input);
        User user2 = userMapper.findUserByUserPhone(input);
        if(user != null){
            if(!user.getUserPasscode().equals(userPasscode)){
                return -2;
            }
            else if(user.getUserName().equals(input)||user.getUserPhone().equals(input)){
                if(user.getUserStatus()==1){
                    return -3;
                }
                else{
                    return 0;
                }
            }
            else {
                return -2;
            }
        }
        else{
            if(user1 == null){
                if(user2 == null){
                    return -1;
                }
                else if(!user2.getUserPasscode().equals(userPasscode)){
                    return -2;
                }
                else if(user2.getUserStatus()==1){
                    return -3;
                }
                else if(user2.getWechatId() != null){
                    return -4;
                }
                else{
                    user2.setWechatId(openid);
                    int ret = userMapper.updateUserOpenid(user2);
                    if(ret > 0){
                        return 1;
                    }
                    return -4;
                }
            }
            else if(!user1.getUserPasscode().equals(userPasscode)){
                if(user2 == null){
                    return -2;
                }
                else if(!user2.getUserPasscode().equals(userPasscode)){
                    return -2;
                }
                else if(user2.getUserStatus()==1){
                    return -3;
                }
                else if(user2.getWechatId() != null){
                    return -4;
                }
                else{
                    user2.setWechatId(openid);
                    int ret = userMapper.updateUserOpenid(user2);
                    if(ret > 0){
                        return 1;
                    }
                    return -4;
                }
            }
            else if(user1.getUserStatus()==1){
                return -3;
            }
            else{
                user1.setWechatId(openid);
                int ret = userMapper.updateUserOpenid(user1);
                if(ret > 0){
                    return 2;
                }
                return -4;
            }
        }
    }

    /**
     * 用户自动登录
     *
     * @param token
     * @return Response
     */
    @Transactional
    public int userAutoLogin(String token){
        if(token == null){
            return -1;
        }
        String openid = JWT.decode(token).getClaim("openid").as(String.class);
        System.out.println("openid:"+openid);
        Date date1 = new Date(System.currentTimeMillis());
        Date date2 = JWT.decode(token).getExpiresAt();
        System.out.println("现在时间："+date1);
        System.out.println("过期时间："+date2);
        if(date1.after(date2)){
            return -2;
        }
        User user = userMapper.findUserByWechatid(openid);
        if(user == null){
            return -3;
        }
        else{
            return 0;
        }
    }

    public String getOpenId(String code){
        Object obj=  restTemplate.getForObject("https://api.weixin.qq.com/sns/jscode2session?appid=wx1e08950f99ac2df6&secret=de0d5de41bfec5cd697ebb56ca9e1e74&js_code=" + code + "&grant_type=authorization_code",String.class);
        String object1 = obj.toString();
        JSONObject json=new JSONObject(object1);
        Map<String,Object> map=new HashMap<String, Object>();
        Iterator it = json.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            Object value = json.get(key);
            map.put(key, value);
        }
        String openid = (String) map.get("openid");
        return openid;
    }

    public User returnInfoFromUsername(String input){
        User user = userMapper.findUserByUsername(input);
        return user;
    }

    public User returnInfoFromUserPhone(String input){
        User user = userMapper.findUserByUserPhone(input);
        return user;
    }

    public User returnInfoFromWechatid(String openid){
        User user = userMapper.findUserByWechatid(openid);
        return user;
    }

    public User returnInfoFromUserId(Integer userId){
        User user = userMapper.findUserByUserId(userId);
        return user;
    }
}
