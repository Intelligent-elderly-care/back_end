package com.example.smartpensionsystem.service.impl;

import com.example.smartpensionsystem.entity.Event;
import com.example.smartpensionsystem.mapper.EventMapper;
import com.example.smartpensionsystem.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventMapper eventMapper;

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
}
