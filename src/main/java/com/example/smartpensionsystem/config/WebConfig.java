package com.example.smartpensionsystem.config;


import com.example.smartpensionsystem.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录接口,注册接口，swagger不拦截,其它的都需要携带请求头
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/register","/login","/swagger-ui/**", "/v3/api-docs/**");
    }
}