package com.example.smartpensionsystem.mapper;

import com.example.smartpensionsystem.entity.Volunteer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface VolunteerMapper {

    @Select("SELECT * FROM volunteer WHERE id = #{id}")
    Volunteer getVolunteerById(Integer id);

    @Select("SELECT * FROM volunteer")
    List<Volunteer> getAllVolunteers();

    @Insert("INSERT INTO volunteer (name, gender, phone, idCard, birthday, checkinDate, checkoutDate, imgsetDir, description) " +
            "VALUES (#{name}, #{gender}, #{phone}, #{idCard}, #{birthday}, #{checkinDate}, #{checkoutDate}, #{imgsetDir}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertVolunteer(Volunteer volunteer);

    @Update("UPDATE volunteer " +
            "SET name=#{name}, gender=#{gender}, phone=#{phone}, idCard=#{idCard}, birthday=#{birthday}, " +
            "checkinDate=#{checkinDate}, checkoutDate=#{checkoutDate}, imgsetDir=#{imgsetDir}, description=#{description} " +
            "WHERE id=#{id}")
    void updateVolunteer(Volunteer volunteer);

    @Delete("DELETE FROM volunteer WHERE id = #{id}")
    void deleteVolunteer(Integer id);
}
