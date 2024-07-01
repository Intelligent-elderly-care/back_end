package com.example.smartpensionsystem.service;


import com.example.smartpensionsystem.entity.OldPerson;

import java.util.List;

public interface OldPersonService {
    // 根据id查看老人信息
    OldPerson getOldPersonById(int id);
    // 根据身份证号查找老人信息
    OldPerson getOldPersonByIdCard(String id_card);
    // 查看所有老人信息
    List<OldPerson> allOldPersons();
    // 添加老人信息
    void addOldPerson(OldPerson oldPerson);
    // 根据id删除老人信息
    void deleteOldPersonById(int id);
    // 更新老人信息
    void updateOldPerson(OldPerson oldPerson);
    //根据老人名字查询
    List<OldPerson> getOldPersonsByName(String name);
}
