package com.example.smartpensionsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SysUser {
    private Integer id;
    private String username; // 用户名
    @JsonIgnore
    private String password; // 密码
    private String real_name; // 真实姓名
    private String sex;      // 性别
    private String email;    // 邮箱
    private String phone;    // 手机号
    private String description; // 描述
}
