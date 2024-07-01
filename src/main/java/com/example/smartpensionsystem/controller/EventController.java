package com.example.smartpensionsystem.controller;

import com.example.smartpensionsystem.entity.Event;
import com.example.smartpensionsystem.entity.Result;
import com.example.smartpensionsystem.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // 根据id获取事件信息
    @GetMapping("/findById/{id}")
    public Result getEventById(@PathVariable Integer id) {
        Event event = eventService.getEventById(id);
        if (event != null) {
            return Result.success(event);
        } else {
            return Result.error("该id的事件信息不存在");
        }
    }

    // 获取所有事件信息
    @GetMapping("/findAll")
    public Result getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return Result.success(events);
    }

    // 添加事件信息
    @PutMapping("/add")
    public Result insertEvent(@RequestBody Event event) {
        if (event.getEvent_desc() == null) {
            return Result.error("描述不能为空");
        }
        eventService.insertEvent(event);
        return Result.success();
    }

    // 删除事件信息
    @DeleteMapping("/delete/{id}")
    public Result deleteEvent(@PathVariable Integer id) {
        Event event = eventService.getEventById(id);
        if (event == null) {
            return Result.error("该id的事件信息不存在");
        } else {
            eventService.deleteEvent(id);
            return Result.success();
        }
    }

    // 更新事件信息
    @PostMapping("/update")
    public Result updateEvent(@RequestBody Event event) {
        if (eventService.getEventById(event.getId()) == null) {
            return Result.error("该id的事件信息不存在");
        } else {
            eventService.updateEvent(event);
            return Result.success();
        }
    }

    @GetMapping("/findByType/{type}")
    public Result getEventsByType(@PathVariable String type) {
        List<Event> events = eventService.getEventsByType(type);
        if (events != null && !events.isEmpty()) {
            return Result.success(events);
        } else {
            return Result.error("该类型的事件信息不存在");
        }
    }
}
