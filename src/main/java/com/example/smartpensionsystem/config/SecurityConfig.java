package com.example.smartpensionsystem.config;

import com.example.smartpensionsystem.filter.JwtAuthenticationFilter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {
    @Resource
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    private AccessDeniedHandler accessDeniedHandler;
    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // 配置跨域、CSRF、认证、授权等
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*"); // 允许所有域名的跨域请求
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // 允许的请求方法
        configuration.addAllowedHeader("*"); // 允许所有请求头

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 为所有路径的请求添加以上的跨域配置
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // 配置跨域
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 禁用session
                .csrf(csrf -> csrf.disable()) // 禁用 CSRF 保护
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register").permitAll() // 允许对登录API和注册API的访问
                        .anyRequest().authenticated() // 其他请求都需要认证，即登录后才能访问
                )
                .httpBasic(httpBasic -> httpBasic.disable()) // 禁用 HTTP Basic 认证
                .formLogin(formLogin -> formLogin.disable()) // 禁用表单登录
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // 添加 JWT 过滤器
                // 配置异常处理
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(authenticationEntryPoint) // 认证异常处理
                        .accessDeniedHandler(accessDeniedHandler) // 授权异常处理
                );

        return http.build();
    }
}
