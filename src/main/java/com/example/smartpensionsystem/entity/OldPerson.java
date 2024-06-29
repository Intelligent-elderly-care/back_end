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

    public OldPerson(String name, String gender, String phone, String id_card, LocalDateTime checkin_date,
                     LocalDateTime checkout_date, LocalDateTime birthday, String imgsetDir, String roomNumber,
                     String firstGuardianName, String firstGuardianRelationship, String firstGuardianPhone,
                     String firstGuardianWechat, String secondGuardianName, String secondGuardianRelationship,
                     String secondGuardianPhone, String secondGuardianWechat, String healthState, String description) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.id_card = id_card;
        this.checkin_date = checkin_date;
        this.checkout_date = checkout_date;
        this.birthday = birthday;
        this.imgsetDir = imgsetDir;
        this.roomNumber = roomNumber;
        this.firstGuardianName = firstGuardianName;
        this.firstGuardianRelationship = firstGuardianRelationship;
        this.firstGuardianPhone = firstGuardianPhone;
        this.firstGuardianWechat = firstGuardianWechat;
        this.secondGuardianName = secondGuardianName;
        this.secondGuardianRelationship = secondGuardianRelationship;
        this.secondGuardianPhone = secondGuardianPhone;
        this.secondGuardianWechat = secondGuardianWechat;
        this.healthState = healthState;
        this.description = description;
    }
}
