package com.example.smartpensionsystem.mapper;

import com.example.smartpensionsystem.entity.Employee;
import com.example.smartpensionsystem.entity.OldPerson;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("SELECT * FROM employee_info WHERE id = #{id}")
    Employee getEmployeeById(Integer id);

    @Select("select * from  employee_info where id_card=#{id_card}")
    Employee getEmployeeByIdCard(String id_card);

    @Select("SELECT * FROM employee_info")
    List<Employee> getAllEmployees();

    @Insert("INSERT INTO employee_info (name, gender, phone, id_card, birthday, hire_date, resign_date, imgset_dir, description,oldperson_id,volunteer_id) " +
            "VALUES (#{name}, #{gender}, #{phone}, #{id_card}, #{birthday}, #{hire_date}, #{resign_date}, #{imgset_dir}, #{description},#{oldperson_id},#{volunteer_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertEmployee(Employee employee);

    @Update("UPDATE employee_info " +
            "SET name=#{name}, gender=#{gender}, phone=#{phone}, id_card=#{id_card}, birthday=#{birthday}, " +
            "hire_date=#{hire_date}, resign_date=#{resign_date}, imgset_dir=#{imgset_dir}, description=#{description} ,oldperson_id=#{oldperson_id},volunteer_id=#{volunteer_id} " +
            "WHERE id=#{id}")
    void updateEmployee(Employee employee);

    @Delete("DELETE FROM employee_info WHERE id = #{id}")
    void deleteEmployee(Integer id);

    @Select("SELECT * FROM employee_info WHERE name = #{name}")
    List<Employee> getEmployeesByName(String name);
}
