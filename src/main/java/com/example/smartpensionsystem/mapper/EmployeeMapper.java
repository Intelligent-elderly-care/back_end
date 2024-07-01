package com.example.smartpensionsystem.mapper;

import com.example.smartpensionsystem.entity.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("SELECT * FROM employee_info WHERE id = #{id}")
    Employee getEmployeeById(Integer id);

    @Select("SELECT * FROM employee_info")
    List<Employee> getAllEmployees();

    @Insert("INSERT INTO employee_info (name, gender, phone, id_card, birthday, hire_date, resign_date, imgset_dir, description) " +
            "VALUES (#{name}, #{gender}, #{phone}, #{id_card}, #{birthday}, #{hire_date}, #{resign_date}, #{imgset_dir}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertEmployee(Employee employee);

    @Update("UPDATE employee_info " +
            "SET name=#{name}, gender=#{gender}, phone=#{phone}, id_card=#{id_card}, birthday=#{birthday}, " +
            "hire_date=#{hire_date}, resign_date=#{resign_date}, imgset_dir=#{imgset_dir}, description=#{description} " +
            "WHERE id=#{id}")
    void updateEmployee(Employee employee);

    @Delete("DELETE FROM employee_info WHERE id = #{id}")
    void deleteEmployee(Integer id);

    @Select("SELECT * FROM employee_info WHERE name = #{name}")
    List<Employee> getEmployeesByName(String name);
}
