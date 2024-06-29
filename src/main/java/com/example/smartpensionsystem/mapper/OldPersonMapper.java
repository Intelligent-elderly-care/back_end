package com.example.smartpensionsystem.mapper;

import com.example.smartpensionsystem.entity.OldPerson;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OldPersonMapper {

    @Select("selete * from oldperson_info where id =#{id}")
    OldPerson getOldPersonById(int id);

    @Select("select * from oldperson_info where id_card=#{idCard}")
    OldPerson getOldPersonByIdCard(String idCard);

    @Select("selete * from oldperson_info")
    List<OldPerson> allOldPersons();

    @Insert("insert into oldperson_info(name, gender, phone, id_card, birthday, checkin_date, checkout_date, room_number, imgset_dir, firstguardian_name, firstguardian_relationship, firstguardian_phone, firstguardian_wechat, secondguardian_name, secondguardian_relationship, secondguardian_phone, secondguardian_wechat, health_state, description) "
            + "values (#{name}, #{gender}, #{phone}, #{id_card}, #{birthday}, #{checkin_date}, #{checkout_date}, #{room_number}, #{imgsetDir}, #{firstGuardianName}, #{firstGuardianRelationship}, #{firstGuardianPhone}, #{firstGuardianWechat}, #{secondGuardianName}, #{secondGuardianRelationship}, #{secondGuardianPhone}, #{secondGuardianWechat}, #{healthState}, #{description})")
    void addOldPerson(OldPerson oldPerson);

    @Delete("delete from oldperson_info where id=#{id}")
    void deletePerson(int id);

    @Update("update oldperson_info " +
            "set name=#{name}, " +
            "gender=#{gender}, " +
            "phone=#{phone}, " +
            "id_card=#{id_card}, " +
            "birthday=#{birthday}, " +
            "checkin_date=#{checkin_date}, " +
            "checkout_date=#{checkout_date}, " +
            "room_number=#{room_number}, " +
            "imgset_dir=#{imgsetDir}, " +
            "firstguardian_name=#{firstGuardianName}, " +
            "firstguardian_relationship=#{firstGuardianRelationship}, " +
            "firstguardian_phone=#{firstGuardianPhone}, " +
            "firstguardian_wechat=#{firstGuardianWechat}, " +
            "secondguardian_name=#{secondGuardianName}, " +
            "secondguardian_relationship=#{secondGuardianRelationship}, " +
            "secondguardian_phone=#{secondGuardianPhone}, " +
            "secondguardian_wechat=#{secondGuardianWechat}, " +
            "health_state=#{healthState}, " +
            "description=#{description} " +
            "where id=#{id}")
    void updateOldPerson(OldPerson oldPerson);


}
