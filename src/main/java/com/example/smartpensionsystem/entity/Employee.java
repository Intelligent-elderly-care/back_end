package com.example.smartpensionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    private Integer id;
    private String name;
    private String gender;
    private String phone;
    private String id_card;
    private LocalDateTime birthday;
    private LocalDateTime hire_date; // 入职日期
    private LocalDateTime resign_date;  // 离职日期
    private String imgset_dir; // 头像url地址
    private String description; // 描述
}
