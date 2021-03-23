package com.whu.miniapp.Interceptor;

import com.whu.miniapp.entity.User;
import com.whu.miniapp.mapper.UserMapper;
import com.whu.miniapp.util.SpringUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


/**
 * Author: 胡龙晨
 * Date: 2021-01-31
 * 获取token并验证token
 */

public class FilterInterceptor implements HandlerInterceptor {
    UserMapper userMapper = SpringUtil.getBean(UserMapper.class);

    //拦截器：请求之前preHandle
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        //检查有没有需要用户权限的注解
        //如果有注解Authorize，就需要验证token
        String token = httpServletRequest.getHeader("Authorization");// 从 http 请求头中取出 token

        System.out.println("请求头中的token:"+token);
        // 执行认证
        if (token == null) {
            httpServletResponse.setHeader("resInfo","no token");
            throw new RuntimeException("无token，请重新登录");
        }

        Date date1 = new Date(System.currentTimeMillis());
        Date date2 = JWT.decode(token).getExpiresAt();
        System.out.println("现在时间："+date1);
        System.out.println("过期时间："+date2);
        if(date1.after(date2)){
            httpServletResponse.setHeader("resInfo","token overdue");
            throw new RuntimeException("token过期，请重新登陆");
        }

        String user_id = JWT.decode(token).getKeyId();
        System.out.println("user_id:"+user_id);
        int userId = Integer.parseInt(user_id);
        System.out.println("userId:"+userId);
        // 添加request参数，用于传递userid
        httpServletRequest.setAttribute("userId", userId);
        // 根据userId 查询用户信息
        User user = userMapper.findUserByUserId(userId);
        System.out.println("user:"+user);
        if (user == null) {
            httpServletResponse.setHeader("resInfo","no user");
            throw new RuntimeException("用户不存在，请重新登录");
        }

        String openid = JWT.decode(token).getClaim("openid").as(String.class);
        System.out.println("openid:"+openid);
        // 添加request参数，用于传递openid
        if (!user.getWechatId().equals(openid)){
            httpServletResponse.setHeader("resInfo","openid incorrect");
            throw new RuntimeException("当前用户不符，请重新登陆");
        }
        httpServletRequest.setAttribute("openid", openid);

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUserPasscode())).build();
        System.out.println("jwtVerifier:"+jwtVerifier);
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            httpServletResponse.setHeader("resInfo","passcode incorrect");
            throw new RuntimeException("密码有误，请重新登陆");
        }
        httpServletResponse.setHeader("resInfo","succeess");
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    //拦截器：请求之后：afterCompletion
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
