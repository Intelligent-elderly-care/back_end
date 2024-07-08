package com.example.smartpensionsystem.mapper;

import com.example.smartpensionsystem.entity.Event;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface EventMapper {

    @Select("SELECT * FROM event_info WHERE id = #{id}")
    Event getEventById(Integer id);

    @Select("SELECT * FROM event_info")
    List<Event> getAllEvents();

    @Insert("INSERT INTO event_info (event_type, event_date, event_location, event_desc, oldperson_id) " +
            "VALUES (#{event_type}, #{event_date}, #{event_location}, #{event_desc}, #{oldperson_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertEvent(Event event);

    @Update("UPDATE event_info " +
            "SET event_type=#{event_type}, event_date=#{event_date}, event_location=#{event_location}, event_desc=#{event_desc}, oldperson_id=#{oldperson_id} " +
            "WHERE id=#{id}")
    void updateEvent(Event event);

    @Delete("DELETE FROM event_info WHERE id = #{id}")
    void deleteEvent(Integer id);

    @Select("SELECT * FROM event_info WHERE event_type = #{event_type}")
    List<Event> getEventsByType(String event_type);
}
