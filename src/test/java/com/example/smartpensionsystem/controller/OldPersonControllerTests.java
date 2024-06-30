package com.example.smartpensionsystem.controller;

import com.example.smartpensionsystem.entity.OldPerson;
import com.example.smartpensionsystem.service.OldPersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class OldPersonControllerTests {

    private MockMvc mockMvc;

    @Mock
    private OldPersonService oldPersonService;

    @InjectMocks
    private OldPersonController oldPersonController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(oldPersonController).build();
    }

    @Test
    public void testGetOldPersonById() throws Exception {
        OldPerson oldPerson = new OldPerson(1, "John Doe", "Male", "1234567890", "123456789012345678", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), "http://example.com/image.jpg", "101", "Jane Doe", "Daughter", "0987654321", "jane_wechat", "Jim Doe", "Son", "1122334455", "jim_wechat", "Healthy", "Description");

        // 模拟服务层返回数据
        when(oldPersonService.getOldPersonById(1)).thenReturn(oldPerson);

        mockMvc.perform(get("/oldpersons/findById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data.name").value("John Doe"))
                .andDo(print());
    }

    @Test
    public void testGetAllOldPersons() throws Exception {
        mockMvc.perform(get("/oldpersons/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andDo(print());
    }

    @Test
    public void testAddOldPerson() throws Exception {
        OldPerson oldPerson = new OldPerson("John Doe", "Male", "1234567890", "123456789012345678",
                LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), "http://example.com/image.jpg",
                "101", "Jane Doe", "Daughter", "0987654321", "jane_wechat", "Jim Doe", "Son",
                "1122334455", "jim_wechat", "Healthy", "Description");

        // 模拟返回null以便表示此人信息不存在
        when(oldPersonService.getOldPersonByIdCard(anyString())).thenReturn(null);
        // 使用 doNothing 来处理 void 方法
        doNothing().when(oldPersonService).addOldPerson(any(OldPerson.class));

        mockMvc.perform(put("/oldpersons/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"John Doe\", \"gender\": \"Male\", \"phone\": \"1234567890\", \"id_card\": \"123456789012345678\", \"checkin_date\": \"2023-06-01T00:00:00\", \"checkout_date\": \"2023-06-10T00:00:00\", \"birthday\": \"1950-01-01T00:00:00\", \"imgset_dir\": \"http://example.com/image.jpg\", \"room_number\": \"101\", \"firstguardian_name\": \"Jane Doe\", \"firstguardian_relationship\": \"Daughter\", \"firstguardian_phone\": \"0987654321\", \"firstguardian_wechat\": \"jane_wechat\", \"secondguardian_name\": \"Jim Doe\", \"secondguardian_relationship\": \"Son\", \"secondguardian_phone\": \"1122334455\", \"secondguardian_wechat\": \"jim_wechat\", \"health_state\": \"Healthy\", \"description\": \"Description\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andDo(print());
    }


    @Test
    public void testDeleteOldPerson() throws Exception {
        OldPerson oldPerson = new OldPerson(1, "John Doe", "Male", "1234567890", "123456789012345678", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), "http://example.com/image.jpg", "101", "Jane Doe", "Daughter", "0987654321", "jane_wechat", "Jim Doe", "Son", "1122334455", "jim_wechat", "Healthy", "Description");

        when(oldPersonService.getOldPersonById(1)).thenReturn(oldPerson);

        mockMvc.perform(delete("/oldpersons/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andDo(print());
    }

    @Test
    public void testUpdateOldPerson() throws Exception {
        OldPerson oldPerson = new OldPerson(1, "John Doe", "Male", "1234567890", "123456789012345678", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), "http://example.com/image.jpg", "101", "Jane Doe", "Daughter", "0987654321", "jane_wechat", "Jim Doe", "Son", "1122334455", "jim_wechat", "Healthy", "Description");

        when(oldPersonService.getOldPersonById(1)).thenReturn(oldPerson);

        mockMvc.perform(post("/oldpersons/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"name\": \"John Doe Updated\", \"gender\": \"Male\", \"phone\": \"1234567890\", \"id_card\": \"123456789012345678\", \"checkin_date\": \"2023-06-01T00:00:00\", \"checkout_date\": \"2023-06-10T00:00:00\", \"birthday\": \"1950-01-01T00:00:00\", \"imgset_dir\": \"http://example.com/image.jpg\", \"room_number\": \"101\", \"firstguardian_name\": \"Jane Doe\", \"firstguardian_relationship\": \"Daughter\", \"firstguardian_phone\": \"0987654321\", \"firstguardian_wechat\": \"jane_wechat\", \"secondguardian_name\": \"Jim Doe\", \"secondguardian_relationship\": \"Son\", \"secondguardian_phone\": \"1122334455\", \"secondguardian_wechat\": \"jim_wechat\", \"health_state\": \"Healthy\", \"description\": \"Description\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andDo(print());
    }
}
