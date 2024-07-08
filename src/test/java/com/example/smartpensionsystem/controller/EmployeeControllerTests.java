package com.example.smartpensionsystem.controller;

import com.example.smartpensionsystem.entity.Employee;
import com.example.smartpensionsystem.entity.Result;
import com.example.smartpensionsystem.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EmployeeControllerTests {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Employee employee = new Employee(1, "John Doe", "Male", "1234567890", "123456789012345678", LocalDateTime.now(), LocalDateTime.now(), null, "img_url", "description");

        when(employeeService.getEmployeeById(1)).thenReturn(employee);

        mockMvc.perform(get("/employees/findById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name", is("John Doe")))
                .andExpect(jsonPath("$.data.gender", is("Male")));

        verify(employeeService, times(1)).getEmployeeById(1);
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "John Doe", "Male", "1234567890", "123456789012345678", LocalDateTime.now(), LocalDateTime.now(), null, "img_url", "description"));
        employees.add(new Employee(2, "Jane Doe", "Female", "0987654321", "876543210987654321", LocalDateTime.now(), LocalDateTime.now(), null, "img_url", "description"));

        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/employees/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].name", is("John Doe")))
                .andExpect(jsonPath("$.data[1].name", is("Jane Doe")));

        verify(employeeService, times(1)).getAllEmployees();
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void testInsertEmployee() throws Exception {
        Employee employee = new Employee(1, "John Doe", "Male", "1234567890", "123456789012345678", LocalDateTime.now(), LocalDateTime.now(), null, "img_url", "description");

        when(employeeService.getEmployeeById(1)).thenReturn(null);

        mockMvc.perform(put("/employees/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"John Doe\",\"gender\":\"Male\",\"phone\":\"1234567890\",\"id_card\":\"123456789012345678\",\"birthday\":\"2022-01-01T00:00:00\",\"hire_date\":\"2022-01-01T00:00:00\",\"imgset_dir\":\"img_url\",\"description\":\"description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("操作成功")));

        verify(employeeService, times(1)).getEmployeeById(1);
        verify(employeeService, times(1)).insertEmployee(any(Employee.class));
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        Employee employee = new Employee(1, "John Doe", "Male", "1234567890", "123456789012345678", LocalDateTime.now(), LocalDateTime.now(), null, "img_url", "description");

        when(employeeService.getEmployeeById(1)).thenReturn(employee);

        mockMvc.perform(delete("/employees/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("操作成功")));

        verify(employeeService, times(1)).getEmployeeById(1);
        verify(employeeService, times(1)).deleteEmployee(1);
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        Employee employee = new Employee(1, "John Doe", "Male", "1234567890", "123456789012345678", LocalDateTime.now(), LocalDateTime.now(), null, "img_url", "description");

        when(employeeService.getEmployeeById(1)).thenReturn(employee);

        mockMvc.perform(post("/employees/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"John Doe\",\"gender\":\"Male\",\"phone\":\"1234567890\",\"id_card\":\"123456789012345678\",\"birthday\":\"2022-01-01T00:00:00\",\"hire_date\":\"2022-01-01T00:00:00\",\"imgset_dir\":\"img_url\",\"description\":\"description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("操作成功")));

        verify(employeeService, times(1)).getEmployeeById(1);
        verify(employeeService, times(1)).updateEmployee(any(Employee.class));
        verifyNoMoreInteractions(employeeService);
    }
}
