package com.example.smartpensionsystem.service;

import com.example.smartpensionsystem.entity.Volunteer;

import java.util.List;

public interface VolunteerService {
    //根据id查看义工信息
    Volunteer getVolunteerById(Integer id);
    //查看所有义工信息
    List<Volunteer> getAllVolunteers();
    //添加义工
    void insertVolunteer(Volunteer volunteer);
    //更新义工
    void updateVolunteer(Volunteer volunteer);
    //删除义工
    void deleteVolunteer(Integer id);
    //根据名字查询义工
    List<Volunteer> getVolunteersByName(String name);
}
