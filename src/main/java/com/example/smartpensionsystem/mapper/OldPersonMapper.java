package com.example.smartpensionsystem.mapper;

import com.example.smartpensionsystem.entity.OldPerson;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OldPersonMapper {

    @Select("select * from oldperson_info where id =#{id}")
    OldPerson getOldPersonById(int id);

    @Select("select * from oldperson_info where id_card=#{id_card}")
    OldPerson getOldPersonByIdCard(String id_card);

    @Select("select * from oldperson_info")
    List<OldPerson> allOldPersons();

    @Insert("insert into oldperson_info(name, gender, phone, id_card, birthday, checkin_date, checkout_date, room_number, imgset_dir, firstguardian_name, firstguardian_relationship, firstguardian_phone, firstguardian_wechat, secondguardian_name, secondguardian_relationship, secondguardian_phone, secondguardian_wechat, health_state, description) "
            + "values (#{name}, #{gender}, #{phone}, #{id_card}, #{birthday}, #{checkin_date}, #{checkout_date}, #{room_number}, #{imgset_dir}, #{firstguardian_name}, #{firstguardian_relationship}, #{firstguardian_phone}, #{firstguardian_wechat}, #{secondguardian_name}, #{secondguardian_relationship}, #{secondguardian_phone}, #{secondguardian_wechat}, #{health_state}, #{description})")

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
            "imgset_dir=#{imgset_dir}, " +
            "firstguardian_name=#{firstguardian_name}, " +
            "firstguardian_relationship=#{firstguardian_relationship}, " +
            "firstguardian_phone=#{firstguardian_phone}, " +
            "firstguardian_wechat=#{firstguardian_wechat}, " +
            "secondguardian_name=#{secondguardian_name}, " +
            "secondguardian_relationship=#{secondguardian_relationship}, " +
            "secondguardian_phone=#{secondguardian_phone}, " +
            "secondguardian_wechat=#{secondguardian_wechat}, " +
            "health_state=#{health_state}, " +
            "description=#{description} " +
            "where id=#{id}")
    void updateOldPerson(OldPerson oldPerson);

    @Select("SELECT * FROM old_person_info WHERE name = #{name}")
    OldPerson getOldPersonsByName(String name);
}
