package com.example.smartpensionsystem.controller;

import com.example.smartpensionsystem.entity.Result;
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

    // 根据id获取志愿者信息
    @GetMapping("/findById/{id}")
    public Result getVolunteerById(@PathVariable Integer id) {
        Volunteer volunteer = volunteerService.getVolunteerById(id);
        if (volunteer != null) {
            return Result.success(volunteer);
        } else {
            return Result.error("该id的志愿者信息不存在");
        }
    }

    // 获取所有志愿者信息
    @GetMapping("/findAll")
    public Result getAllVolunteers() {
        List<Volunteer> volunteers = volunteerService.getAllVolunteers();
        return Result.success(volunteers);
    }

    // 添加志愿者信息
    @PutMapping("/add")
    public Result insertVolunteer(@RequestBody Volunteer volunteer) {
        if (volunteer.getName() == null) {
            return Result.error("姓名不能为空");
        }
        volunteerService.insertVolunteer(volunteer);
        return Result.success();
    }

    // 更新志愿者信息
    @PostMapping("/update")
    public Result updateVolunteer(@RequestBody Volunteer volunteer) {
        if (volunteerService.getVolunteerById(volunteer.getId()) == null) {
            return Result.error("该id的志愿者信息不存在");
        } else {
            volunteerService.updateVolunteer(volunteer);
            return Result.success();
        }
    }

    // 删除志愿者信息
    @DeleteMapping("/delete/{id}")
    public Result deleteVolunteer(@PathVariable Integer id) {
        Volunteer volunteer = volunteerService.getVolunteerById(id);
        if (volunteer == null) {
            return Result.error("该id的志愿者信息不存在");
        } else {
            volunteerService.deleteVolunteer(id);
            return Result.success();
        }
    }

    // 根据姓名获取志愿者信息
    @GetMapping("/findByName")
    public Result getVolunteersByName(@RequestParam("name") String name) {
        List<Volunteer> volunteers = volunteerService.getVolunteersByName(name);
        if (volunteers != null && !volunteers.isEmpty()) {
            return Result.success(volunteers);
        } else {
            return Result.error("该姓名的志愿者信息不存在");
        }
    }

}
