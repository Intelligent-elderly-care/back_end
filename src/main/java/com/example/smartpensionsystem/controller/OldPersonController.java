package com.example.smartpensionsystem.controller;

import com.example.smartpensionsystem.entity.OldPerson;
import com.example.smartpensionsystem.entity.Result;
import com.example.smartpensionsystem.service.OldPersonService;
import com.example.smartpensionsystem.utils.AliOssUtils;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
public class OldPersonController {
    @Autowired
    private OldPersonService oldPersonService;

    // 根据id获取老人信息
    @GetMapping("/oldperson/{id}")
    public Result getOldPersonById(@PathVariable int id){
        OldPerson oldPerson=oldPersonService.getOldPersonById(id);
        if(oldPerson==null){
            return Result.error("该id的老人信息不存在");
        }else{
            return Result.success(oldPerson);
        }
    }

    // 获取所有老人信息
    @GetMapping("/oldperson/all")
    public Result getAllOldPersons(){
        return Result.success(oldPersonService.allOldPersons());
    }

    // 添加老人信息
    @PutMapping("/oldperson")
    public Result addOldPerson(@RequestBody OldPerson oldPerson) throws Exception {
        if (oldPerson.getName() == null) {
            return Result.error("姓名不能为空");
        } else if (oldPerson.getId_card() == null) {
            return Result.error("身份证不能为空");
        } else {
            OldPerson existingOldPerson = oldPersonService.getOldPersonByIdCard(oldPerson.getId_card());
            if (existingOldPerson != null && existingOldPerson.getId_card().equals(oldPerson.getId_card())) {
                return Result.error("此人信息已存在，请勿重复插入");
            }
        }
//        // 把文件的内容储存到本地磁盘上
//        String originalFilename = file.getOriginalFilename();
//        // 保证文件名是唯一的，从而防止文件覆盖
//        String filename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
//        String url= AliOssUtils.uploadFile(filename,file.getInputStream());
//        // 设置头像路径
//        oldPerson.setImgsetDir(url);
        oldPersonService.addOldPerson(oldPerson);
        return Result.success();
    }


    // 修改老人信息
    @DeleteMapping("/oldperson/{id}")
    public Result deleteOldPerson(@PathVariable int id){
        OldPerson oldPerson=oldPersonService.getOldPersonById(id);
        if(oldPerson==null){
            return Result.error("该id的老人信息不存在");
        }else{
            oldPersonService.deleteOldPersonById(id);
            return Result.success();
        }
    }

    // 更新老人信息
    @PostMapping("/oldperson")
    public Result updateOldPerson(@RequestBody OldPerson oldPerson){
        if(oldPersonService.getOldPersonById(oldPerson.getId())==null){
            return Result.error("该id的老人信息不存在");
        }else{
            oldPersonService.updateOldPerson(oldPerson);
            return Result.success();
        }
    }

}
