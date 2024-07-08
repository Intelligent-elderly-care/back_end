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
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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
        OldPerson oldPerson = new OldPerson(1, "John Doe", "M", "123456789", "123456789012345678",
                LocalDateTime.now(), LocalDateTime.now().plusDays(30), LocalDateTime.of(1940, 1, 1, 0, 0),
                "img_url", "101", "Jane Doe", "daughter", "987654321", "jane_wechat",
                "James Doe", "son", "987654322", "james_wechat", "healthy", "description");

        when(oldPersonService.getOldPersonById(1)).thenReturn(oldPerson);

        mockMvc.perform(get("/oldpersons/findById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data.name").value("John Doe"))
                .andDo(print());
    }

    @Test
    public void testGetAllOldPersons() throws Exception {
        OldPerson oldPerson = new OldPerson(1, "John Doe", "M", "123456789", "123456789012345678",
                LocalDateTime.now(), LocalDateTime.now().plusDays(30), LocalDateTime.of(1940, 1, 1, 0, 0),
                "img_url", "101", "Jane Doe", "daughter", "987654321", "jane_wechat",
                "James Doe", "son", "987654322", "james_wechat", "healthy", "description");
        List<OldPerson> oldPersons = Arrays.asList(oldPerson);

        when(oldPersonService.allOldPersons()).thenReturn(oldPersons);

        mockMvc.perform(get("/oldpersons/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data[0].name").value("John Doe"))
                .andDo(print());
    }

    @Test
    public void testAddOldPerson() throws Exception {
        OldPerson oldPerson = new OldPerson(1, "John Doe", "M", "123456789", "123456789012345678",
                LocalDateTime.now(), LocalDateTime.now().plusDays(30), LocalDateTime.of(1940, 1, 1, 0, 0),
                "img_url", "101", "Jane Doe", "daughter", "987654321", "jane_wechat",
                "James Doe", "son", "987654322", "james_wechat", "healthy", "description");

        when(oldPersonService.getOldPersonByIdCard("123456789012345678")).thenReturn(null);
        doNothing().when(oldPersonService).addOldPerson(any(OldPerson.class));

        mockMvc.perform(put("/oldpersons/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"John Doe\", \"gender\": \"M\", \"phone\": \"123456789\", \"id_card\": \"123456789012345678\", \"checkin_date\": \"2023-06-01T00:00:00\", \"checkout_date\": \"2023-07-01T00:00:00\", \"birthday\": \"1940-01-01T00:00:00\", \"imgset_dir\": \"img_url\", \"room_number\": \"101\", \"firstguardian_name\": \"Jane Doe\", \"firstguardian_relationship\": \"daughter\", \"firstguardian_phone\": \"987654321\", \"firstguardian_wechat\": \"jane_wechat\", \"secondguardian_name\": \"James Doe\", \"secondguardian_relationship\": \"son\", \"secondguardian_phone\": \"987654322\", \"secondguardian_wechat\": \"james_wechat\", \"health_state\": \"healthy\", \"description\": \"description\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").value("操作成功"))
                .andDo(print());

        oldPerson.setName(null);
        mockMvc.perform(put("/oldpersons/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": null, \"gender\": \"M\", \"phone\": \"123456789\", \"id_card\": \"123456789012345678\", \"checkin_date\": \"2023-06-01T00:00:00\", \"checkout_date\": \"2023-07-01T00:00:00\", \"birthday\": \"1940-01-01T00:00:00\", \"imgset_dir\": \"img_url\", \"room_number\": \"101\", \"firstguardian_name\": \"Jane Doe\", \"firstguardian_relationship\": \"daughter\", \"firstguardian_phone\": \"987654321\", \"firstguardian_wechat\": \"jane_wechat\", \"secondguardian_name\": \"James Doe\", \"secondguardian_relationship\": \"son\", \"secondguardian_phone\": \"987654322\", \"secondguardian_wechat\": \"james_wechat\", \"health_state\": \"healthy\", \"description\": \"description\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.message").value("姓名不能为空"))
                .andDo(print());
    }

    @Test
    public void testGetOldPersonsByName() throws Exception {
        OldPerson oldPerson = new OldPerson(1, "John Doe", "M", "123456789", "123456789012345678",
                LocalDateTime.now(), LocalDateTime.now().plusDays(30), LocalDateTime.of(1940, 1, 1, 0, 0),
                "img_url", "101", "Jane Doe", "daughter", "987654321", "jane_wechat",
                "James Doe", "son", "987654322", "james_wechat", "healthy", "description");

        when(oldPersonService.getOldPersonsByName("John Doe")).thenReturn(oldPerson);

        mockMvc.perform(get("/oldpersons/findByName")
                        .param("name", "John Doe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data.name").value("John Doe"))
                .andDo(print());
    }

    @Test
    public void testDeleteOldPerson() throws Exception {
        OldPerson oldPerson = new OldPerson(1, "John Doe", "M", "123456789", "123456789012345678",
                LocalDateTime.now(), LocalDateTime.now().plusDays(30), LocalDateTime.of(1940, 1, 1, 0, 0),
                "img_url", "101", "Jane Doe", "daughter", "987654321", "jane_wechat",
                "James Doe", "son", "987654322", "james_wechat", "healthy", "description");

        when(oldPersonService.getOldPersonById(1)).thenReturn(oldPerson);
        doNothing().when(oldPersonService).deleteOldPersonById(1);

        mockMvc.perform(delete("/oldpersons/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").value("操作成功"))
                .andDo(print());
    }

    @Test
    public void testUpdateOldPerson() throws Exception {
        OldPerson oldPerson = new OldPerson(1, "John Doe", "M", "123456789", "123456789012345678",
                LocalDateTime.now(), LocalDateTime.now().plusDays(30), LocalDateTime.of(1940, 1, 1, 0, 0),
                "img_url", "101", "Jane Doe", "daughter", "987654321", "jane_wechat",
                "James Doe", "son", "987654322", "james_wechat", "healthy", "description");

        when(oldPersonService.getOldPersonById(1)).thenReturn(oldPerson);
        doNothing().when(oldPersonService).updateOldPerson(any(OldPerson.class));

        mockMvc.perform(post("/oldpersons/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"name\": \"John Doe\", \"gender\": \"M\", \"phone\": \"123456789\", \"id_card\": \"123456789012345678\", \"checkin_date\": \"2023-06-01T00:00:00\", \"checkout_date\": \"2023-07-01T00:00:00\", \"birthday\": \"1940-01-01T00:00:00\", \"imgset_dir\": \"updated_img_url\", \"room_number\": \"101\", \"firstguardian_name\": \"Jane Doe\", \"firstguardian_relationship\": \"daughter\", \"firstguardian_phone\": \"987654321\", \"firstguardian_wechat\": \"jane_wechat\", \"secondguardian_name\": \"James Doe\", \"secondguardian_relationship\": \"son\", \"secondguardian_phone\": \"987654322\", \"secondguardian_wechat\": \"james_wechat\", \"health_state\": \"healthy\", \"description\": \"updated description\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").value("操作成功"))
                .andDo(print());
    }
}
