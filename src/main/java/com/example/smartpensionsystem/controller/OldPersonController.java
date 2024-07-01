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
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/oldpersons")
public class OldPersonController {
    @Autowired
    private OldPersonService oldPersonService;

    // 根据id获取老人信息
    @GetMapping("/findById/{id}")
    public Result getOldPersonById(@PathVariable int id){
        OldPerson oldPerson=oldPersonService.getOldPersonById(id);
        if(oldPerson==null){
            return Result.error("该id的老人信息不存在");
        }else{
            return Result.success(oldPerson);
        }
    }

    // 获取所有老人信息
    @GetMapping("/findAll")
    public Result getAllOldPersons(){
        return Result.success(oldPersonService.allOldPersons());
    }

    // 添加老人信息
    @PutMapping("/add")
    public Result addOldPerson(@RequestBody OldPerson oldPerson) throws Exception {
        System.out.println(oldPersonService.getOldPersonByIdCard(oldPerson.getId_card()));
        if(oldPerson.getName()==null){
            return Result.error("姓名不能为空");
        }else if(oldPerson.getId_card()==null){
            return  Result.error("身份证不能为空");
        }else if(oldPersonService.getOldPersonByIdCard(oldPerson.getId_card())!=null){
            return Result.error("此人信息已存在，请勿重复插入");
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

    // 根据姓名获取老人信息
    @GetMapping("/findByName")
    public Result getOldPersonsByName(@RequestParam("name") String name) {
        List<OldPerson> oldPersons = oldPersonService.getOldPersonsByName(name);
        if (oldPersons.isEmpty()) {
            return Result.error("该姓名的老人信息不存在");
        } else {
            return Result.success(oldPersons);
        }
    }


    // 删除老人信息
    @DeleteMapping("/delete/{id}")
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
    @PostMapping("/update")
    public Result updateOldPerson(@RequestBody OldPerson oldPerson){
        if(oldPersonService.getOldPersonById(oldPerson.getId())==null){
            return Result.error("该id的老人信息不存在");
        }else{
            oldPersonService.updateOldPerson(oldPerson);
            return Result.success();
        }
    }

}
