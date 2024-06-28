package com.example.smartpensionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OldPerson {
    private int id;
    private String name; // 名字
    private String gender;  // 性别
    private String phone;  // 手机号
    private String id_card; // 身份证
    private LocalDateTime checkin_date; // 入养老院日期
    private LocalDateTime checkout_date;  // 出养老院日期
    private LocalDateTime birthday; // 生日
    private String imgsetDir;   // 头像url地址
    private String roomNumber;  // 房间号
    private String firstGuardianName;  // 第一监护人名字
    private String firstGuardianRelationship;  // 与第一监护人关系
    private String firstGuardianPhone;  // 第一监护人电话
    private String firstGuardianWechat;  // 第一监护人微信
    private String secondGuardianName;   // 第二监护人名字
    private String secondGuardianRelationship;  // 与第二监护人关系
    private String secondGuardianPhone;  // 第二监护人手机号
    private String secondGuardianWechat;  // 第二监护人微信
    private String healthState;  // 健康状况
    private String description; // 描述
}
