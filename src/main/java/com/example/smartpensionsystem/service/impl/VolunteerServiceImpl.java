package com.example.smartpensionsystem.service.impl;

import com.example.smartpensionsystem.entity.Volunteer;
import com.example.smartpensionsystem.mapper.VolunteerMapper;
import com.example.smartpensionsystem.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    private VolunteerMapper volunteerMapper;

    @Override
    public Volunteer getVolunteerById(Integer id) {
        return volunteerMapper.getVolunteerById(id);
    }

    @Override
    public List<Volunteer> getAllVolunteers() {
        return volunteerMapper.getAllVolunteers();
    }

    @Override
    public void insertVolunteer(Volunteer volunteer) {
        volunteerMapper.insertVolunteer(volunteer);
    }

    @Override
    public void updateVolunteer(Volunteer volunteer) {
        volunteerMapper.updateVolunteer(volunteer);
    }

    @Override
    public void deleteVolunteer(Integer id) {
        volunteerMapper.deleteVolunteer(id);
    }
}
