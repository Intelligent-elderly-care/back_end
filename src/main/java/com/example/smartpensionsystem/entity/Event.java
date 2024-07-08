package com.example.smartpensionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Event {
    private int id;
    private int event_type; // 事件类型，0代表情感检测，1代表义工交互检测，2代表陌生人检测，3代表摔倒检测，4代表禁止区,5代表危险物品检测
    private LocalDateTime event_date; // 发生时间
    private String event_location; //地点
    private String event_desc; // 时间描述
    private int oldperson_id;   // 老人id
}

