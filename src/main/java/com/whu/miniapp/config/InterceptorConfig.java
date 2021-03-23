package com.whu.miniapp.config;

import com.whu.miniapp.Interceptor.FilterInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Author: 胡龙晨
 * Date: 2021-01-31
 * 过滤器配置类
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //需要拦截的路径，/**表示需要拦截所有请求
        String[] addPathPatterns = {"/user1/getUserInfo"};
        //不需要拦截的路径,当然要包括登录页面
        String[] excludePathPatterns = {"/user"};
        //注册登录拦截器
        //order指定执行顺序，数值越小越优先
        //可以配置多个拦截器,给registry再加一个Interceptor就可以了，不用再创建一个新的config配置类。
        //拦截器1如果未登录访问首页跳转登录页
        registry.addInterceptor(new FilterInterceptor())
                .addPathPatterns(addPathPatterns)
                .excludePathPatterns(excludePathPatterns)
                .order(0);
    }
}
