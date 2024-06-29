package com.example.smartpensionsystem.controller;

import com.example.smartpensionsystem.entity.Volunteer;
import com.example.smartpensionsystem.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    @GetMapping("/{id}")
    public Volunteer getVolunteerById(@PathVariable Integer id) {
        return volunteerService.getVolunteerById(id);
    }

    @GetMapping
    public List<Volunteer> getAllVolunteers() {
        return volunteerService.getAllVolunteers();
    }

    @PostMapping
    public void insertVolunteer(@RequestBody Volunteer volunteer) {
        volunteerService.insertVolunteer(volunteer);
    }

    @PutMapping("/{id}")
    public void updateVolunteer(@PathVariable Integer id, @RequestBody Volunteer volunteer) {
        volunteer.setId(id);
        volunteerService.updateVolunteer(volunteer);
    }

    @DeleteMapping("/{id}")
    public void deleteVolunteer(@PathVariable Integer id) {
        volunteerService.deleteVolunteer(id);
    }
}
