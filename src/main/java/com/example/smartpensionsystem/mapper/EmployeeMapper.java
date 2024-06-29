package com.example.smartpensionsystem.mapper;

import com.example.smartpensionsystem.entity.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("SELECT * FROM employee WHERE id = #{id}")
    Employee getEmployeeById(Integer id);

    @Select("SELECT * FROM employee")
    List<Employee> getAllEmployees();

    @Insert("INSERT INTO employee (name, gender, phone, idCard, birthday, hireDate, resignDate, imgsetDir, description) " +
            "VALUES (#{name}, #{gender}, #{phone}, #{idCard}, #{birthday}, #{hireDate}, #{resignDate}, #{imgsetDir}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertEmployee(Employee employee);

    @Update("UPDATE employee " +
            "SET name=#{name}, gender=#{gender}, phone=#{phone}, idCard=#{idCard}, birthday=#{birthday}, " +
            "hireDate=#{hireDate}, resignDate=#{resignDate}, imgsetDir=#{imgsetDir}, description=#{description} " +
            "WHERE id=#{id}")
    void updateEmployee(Employee employee);

    @Delete("DELETE FROM employee WHERE id = #{id}")
    void deleteEmployee(Integer id);
}
