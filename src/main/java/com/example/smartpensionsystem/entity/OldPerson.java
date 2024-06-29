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
    private String imgset_dir;   // 头像url地址
    private String room_number;  // 房间号
    private String firstguardian_name;  // 第一监护人名字
    private String firstguardian_relationship;  // 与第一监护人关系
    private String firstguardian_phone;  // 第一监护人电话
    private String firstguardian_wechat;  // 第一监护人微信
    private String secondguardian_name;   // 第二监护人名字
    private String secondguardian_relationship;  // 与第二监护人关系
    private String secondguardian_phone;  // 第二监护人手机号
    private String secondguardian_wechat;  // 第二监护人微信
    private String health_state;  // 健康状况
    private String description; // 描述

    public OldPerson(String name, String gender, String phone, String id_card, LocalDateTime checkin_date,
                     LocalDateTime checkout_date, LocalDateTime birthday, String imgset_dir, String room_number,
                     String firstguardian_name, String firstguardian_relationship, String firstguardian_phone,
                     String firstguardian_wechat, String secondguardian_name, String secondguardian_relationship,
                     String secondguardian_phone, String secondguardian_wechat, String health_state, String description) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.id_card = id_card;
        this.checkin_date = checkin_date;
        this.checkout_date = checkout_date;
        this.birthday = birthday;
        this.imgset_dir = imgset_dir;
        this.room_number = room_number;
        this.firstguardian_name = firstguardian_name;
        this.firstguardian_relationship = firstguardian_relationship;
        this.firstguardian_phone = firstguardian_phone;
        this.firstguardian_wechat = firstguardian_wechat ;
        this.secondguardian_name = secondguardian_name;
        this.secondguardian_relationship = secondguardian_relationship;
        this.secondguardian_phone = secondguardian_phone;
        this.secondguardian_wechat = secondguardian_wechat;
        this.health_state = health_state;
        this.description = description;
    }
}
