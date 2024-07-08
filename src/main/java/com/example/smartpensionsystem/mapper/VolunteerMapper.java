package com.example.smartpensionsystem.mapper;

import com.example.smartpensionsystem.entity.Volunteer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VolunteerMapper {

    @Select("SELECT * FROM volunteer_info WHERE id = #{id}")
    Volunteer getVolunteerById(Integer id);

    @Select("select * from  volunteer_info where id_card=#{id_card}")
    Volunteer getVolunteerByIdCard(String id_card);

    @Select("SELECT * FROM volunteer_info")
    List<Volunteer> getAllVolunteers();

    @Insert("INSERT INTO volunteer_info (name, gender, phone, id_card, birthday, checkin_date, checkout_date, imgset_dir, description,oldperson_id) " +
            "VALUES (#{name}, #{gender}, #{phone}, #{id_card}, #{birthday}, #{checkin_date}, #{checkout_date}, #{imgset_dir}, #{description},#{oldperson_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertVolunteer(Volunteer volunteer);

    @Update("UPDATE volunteer_info " +
            "SET name=#{name}, gender=#{gender}, phone=#{phone}, id_card=#{id_card}, birthday=#{birthday}, " +
            "checkin_date=#{checkin_date}, checkout_date=#{checkout_date}, imgset_dir=#{imgset_dir}, description=#{description} ,oldperson_id=#{oldperson_id} " +
            "WHERE id=#{id}")
    void updateVolunteer(Volunteer volunteer);

    @Delete("DELETE FROM volunteer_info WHERE id = #{id}")
    void deleteVolunteer(Integer id);

    @Select("SELECT * FROM volunteer_info WHERE name = #{name}")
    List<Volunteer> getVolunteersByName(String name);
}
