package com.example.smartpensionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Volunteer {
    private Integer id;
    private String name; // 姓名
    private String gender; // 性别
    private String phone;  // 手机号
    private String id_card; // 身份证
    private LocalDateTime birthday; // 生日
    private LocalDateTime checkin_date; // 访问日期
    private LocalDateTime checkout_date; // 离开日期
    private String imgset_dir; // 头像url地址
    private String description; // 描述
    private int oldperson_id;  // 义工所负责的老人id
}
