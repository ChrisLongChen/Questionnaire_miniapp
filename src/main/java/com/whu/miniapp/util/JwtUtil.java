package com.whu.miniapp.util;

import com.whu.miniapp.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Author: 胡龙晨
 * Date: 2021-01-30
 */

@Service
public class JwtUtil {

    /**
     * JWT 过期时间值 这里写死为和小程序时间一致 7200 秒，也就是两个小时
     */
    private static long expire_time = 7200;

    public String getToken(User user) {
        String userId= user.getId().toString();
        String token="";
        token= JWT.create()
                .withKeyId(userId)
                .withIssuer("www.ikertimes.com")
                .withJWTId("jwt.ikertimes.com")
                .withClaim("openid", user.getWechatId())
                .withExpiresAt(new Date(System.currentTimeMillis() + expire_time*1000))  //JWT 配置过期时间的正确姿势
                .withAudience(userId)
                .sign(Algorithm.HMAC256(user.getUserPasscode()));
        return token;
    }

}
