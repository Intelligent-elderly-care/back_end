package com.example.smartpensionsystem.service.impl;

import com.example.smartpensionsystem.entity.Event;
import com.example.smartpensionsystem.entity.SysUser;
import com.example.smartpensionsystem.mapper.EventMapper;
import com.example.smartpensionsystem.service.EventService;
import com.example.smartpensionsystem.service.SysUserService;
import com.example.smartpensionsystem.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventMapper eventMapper;


    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private EmailService emailService; // 注入邮件发送服务

    @Override
    public Event getEventById(Integer id) {
        return eventMapper.getEventById(id);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventMapper.getAllEvents();
    }

    @Override
    public void insertEvent(Event event) {
        eventMapper.insertEvent(event);

    }

    @Override
    public void updateEvent(Event event) {
        eventMapper.updateEvent(event);
    }

    @Override
    public void deleteEvent(Integer id) {
        eventMapper.deleteEvent(id);
    }

    @Override
    public List<Event> getEventsByType(String event_type) {
        return eventMapper.getEventsByType(event_type);
    }

    @Override
    public void sendDangerousItemEmail(Event event) {
        // 获取所有管理员的邮箱地址
        List<SysUser> admins = sysUserService.getAllAdmins();

        for (SysUser admin : admins) {
            String toEmail = admin.getEmail();
            String subject = "危险物品检测提醒";
            String text = "尊敬的管理员，系统检测到危险物品事件，请及时处理。";

            emailService.sendEmail(toEmail, subject, text);
        }
    }
}
