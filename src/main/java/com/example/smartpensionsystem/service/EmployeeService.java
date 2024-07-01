package com.example.smartpensionsystem.service;

import com.example.smartpensionsystem.entity.Employee;

import java.util.List;

public interface EmployeeService {
    //根据id查看工作人员信息
    Employee getEmployeeById(Integer id);
    //查看所有工作人员
    List<Employee> getAllEmployees();
    //添加工作人员
    void insertEmployee(Employee employee);
    //更新工作人员信息
    void updateEmployee(Employee employee);
    //删除工作人员
    void deleteEmployee(Integer id);
    //根据name查询工作人员
    List<Employee> getEmployeesByName(String name);
}
