package com.example.smartpensionsystem.service;

import com.example.smartpensionsystem.entity.Event;

import java.util.List;

public interface EventService {
    Event getEventById(Integer id);

    List<Event> getAllEvents();

    void insertEvent(Event event);

    void updateEvent(Event event);

    void deleteEvent(Integer id);

    List<Event> getEventsByType(String event_type);

    void sendDangerousItemEmail(Event event);
}
