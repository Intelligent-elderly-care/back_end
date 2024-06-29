package com.example.smartpensionsystem.service;

import com.example.smartpensionsystem.entity.Event;

import java.util.List;

public interface EventService {
    // 根据id查看事件信息
    Event getEventById(Integer id);

    // 查看所有事件
    List<Event> getAllEvents();

    // 添加事件
    void insertEvent(Event event);

    // 更新事件信息
    void updateEvent(Event event);

    // 删除事件
    void deleteEvent(Integer id);
}
