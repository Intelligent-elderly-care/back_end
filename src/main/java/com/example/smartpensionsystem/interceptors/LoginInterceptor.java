package com.example.smartpensionsystem.interceptors;

import com.example.smartpensionsystem.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 令牌验证

        String token=request.getHeader("Authorization");
        if(request.getMethod().equals("OPTIONS")){ // 直接响应数据 （***** 这里是最重要的if ***）
            return true;
        }
        //验证token
        try{

            // 从redis中获取相同的token
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String redisToken = operations.get(token);
            if (redisToken == null) {
                // token已经失效了
                throw new RuntimeException();
            }

            // 验证通过，放行
            return true;
        } catch (Exception e) {
            // http响应状态码为401
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            // 不放行
            return false;
        }
    }
}
