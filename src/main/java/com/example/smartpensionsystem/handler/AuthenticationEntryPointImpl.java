package com.example.smartpensionsystem.handler;

import com.alibaba.fastjson2.JSON;
import com.example.smartpensionsystem.entity.Result;
import com.example.smartpensionsystem.utils.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result<String> result = new Result<>(HttpStatus.UNAUTHORIZED.value(), "用户认证失败请重新登录", null);
        String resMessage = JSON.toJSONString(result);

        // 处理异常
        WebUtil.renderString(response, resMessage);
    }
}
