package com.example.smartpensionsystem.handler;

import com.alibaba.fastjson2.JSON;
import com.example.smartpensionsystem.entity.Result;
import com.example.smartpensionsystem.utils.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result<String> result = new Result<>(HttpStatus.FORBIDDEN.value(), "您没有权限访问该资源", null);
        String resMessage = JSON.toJSONString(result);

        // 处理异常
        WebUtil.renderString(response, resMessage);
    }
}
