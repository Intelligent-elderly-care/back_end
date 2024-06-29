package com.example.smartpensionsystem.controller;

import com.example.smartpensionsystem.entity.Employee;
import com.example.smartpensionsystem.entity.Result;
import com.example.smartpensionsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // 根据id获取员工信息
    @GetMapping("/findById/{id}")
    public Result getEmployeeById(@PathVariable Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return Result.success(employee);
        } else {
            return Result.error("该id的员工信息不存在");
        }
    }

    // 获取所有员工信息
    @GetMapping("/findAll")
    public Result getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return Result.success(employees);
    }

    // 添加员工信息
    @PutMapping("/add")
    public Result insertEmployee(@RequestBody Employee employee) {
        if (employee.getName() == null) {
            return Result.error("姓名不能为空");
        } else if (employee.getIdCard() == null) {
            return Result.error("身份证不能为空");
        } else if (employeeService.getEmployeeById(employee.getId()) != null) {
            return Result.error("此人信息已存在，请勿重复插入");
        }
        employeeService.insertEmployee(employee);
        return Result.success();
    }

    // 删除员工信息
    @DeleteMapping("/delete/{id}")
    public Result deleteEmployee(@PathVariable Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return Result.error("该id的员工信息不存在");
        } else {
            employeeService.deleteEmployee(id);
            return Result.success();
        }
    }

    // 更新员工信息
    @PostMapping("/update")
    public Result updateEmployee(@RequestBody Employee employee) {
        if (employeeService.getEmployeeById(employee.getId()) == null) {
            return Result.error("该id的员工信息不存在");
        } else {
            employeeService.updateEmployee(employee);
            return Result.success();
        }
    }
}
