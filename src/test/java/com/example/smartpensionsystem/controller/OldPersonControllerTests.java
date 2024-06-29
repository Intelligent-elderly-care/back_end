package com.example.smartpensionsystem.controller;

import com.example.smartpensionsystem.entity.OldPerson;
import com.example.smartpensionsystem.entity.Result;
import com.example.smartpensionsystem.service.OldPersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(OldPersonController.class)
public class OldPersonControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OldPersonService oldPersonService;

    private OldPerson oldPerson;

    @BeforeEach
    public void setup() {
        oldPerson = new OldPerson(1, "John Doe", "Male", "1234567890", "123456789012345678",
                LocalDateTime.now(), LocalDateTime.now().plusDays(10), LocalDateTime.of(1950, 1, 1, 0, 0),
                "http://example.com/image.jpg", "101", "Jane Doe", "Daughter", "0987654321", "jane_wechat",
                "Jim Doe", "Son", "1122334455", "jim_wechat", "Healthy", "Description");
    }

    @Test
    public void testGetOldPersonById() throws Exception {
        Mockito.when(oldPersonService.getOldPersonById(1)).thenReturn(oldPerson);

        mockMvc.perform(get("/oldperson/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name", is("John Doe")));
    }

    @Test
    public void testGetOldPersonById_NotFound() throws Exception {
        Mockito.when(oldPersonService.getOldPersonById(1)).thenReturn(null);

        mockMvc.perform(get("/oldperson/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("该id的老人信息不存在")));
    }

    @Test
    public void testGetAllOldPersons() throws Exception {
        Mockito.when(oldPersonService.allOldPersons()).thenReturn(Collections.singletonList(oldPerson));

        mockMvc.perform(get("/oldperson/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name", is("John Doe")));
    }

    @Test
    public void testAddOldPerson() throws Exception {
        // Mock the service method to return null to simulate no existing OldPerson with the same ID card
        Mockito.when(oldPersonService.getOldPersonByIdCard("123456789012345678")).thenReturn(null);
        Mockito.doNothing().when(oldPersonService).addOldPerson(any(OldPerson.class));

        String oldPersonJson = "{ \"name\": \"John Doe\", \"gender\": \"Male\", \"phone\": \"1234567890\", \"id_card\": \"123456789012345678\", \"checkin_date\": \"2023-06-01T00:00:00\", \"checkout_date\": \"2023-06-10T00:00:00\", \"birthday\": \"1950-01-01T00:00:00\", \"imgsetDir\": \"http://example.com/image.jpg\", \"roomNumber\": \"101\", \"firstGuardianName\": \"Jane Doe\", \"firstGuardianRelationship\": \"Daughter\", \"firstGuardianPhone\": \"0987654321\", \"firstGuardianWechat\": \"jane_wechat\", \"secondGuardianName\": \"Jim Doe\", \"secondGuardianRelationship\": \"Son\", \"secondGuardianPhone\": \"1122334455\", \"secondGuardianWechat\": \"jim_wechat\", \"healthState\": \"Healthy\", \"description\": \"Description\" }";

        mockMvc.perform(put("/oldperson")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(oldPersonJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(0)));
    }


    @Test
    public void testDeleteOldPerson() throws Exception {
        Mockito.when(oldPersonService.getOldPersonById(1)).thenReturn(oldPerson);
        Mockito.doNothing().when(oldPersonService).deleteOldPersonById(1);

        mockMvc.perform(delete("/oldperson/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(0)));
    }

    @Test
    public void testUpdateOldPerson() throws Exception {
        Mockito.when(oldPersonService.getOldPersonById(1)).thenReturn(oldPerson);
        Mockito.doNothing().when(oldPersonService).updateOldPerson(any(OldPerson.class));

        String oldPersonJson = "{ \"id\": 1, \"name\": \"John Doe Updated\", \"gender\": \"Male\", \"phone\": \"1234567890\", \"id_card\": \"123456789012345678\", \"checkin_date\": \"2023-06-01T00:00:00\", \"checkout_date\": \"2023-06-10T00:00:00\", \"birthday\": \"1950-01-01T00:00:00\", \"imgsetDir\": \"http://example.com/image.jpg\", \"roomNumber\": \"101\", \"firstGuardianName\": \"Jane Doe\", \"firstGuardianRelationship\": \"Daughter\", \"firstGuardianPhone\": \"0987654321\", \"firstGuardianWechat\": \"jane_wechat\", \"secondGuardianName\": \"Jim Doe\", \"secondGuardianRelationship\": \"Son\", \"secondGuardianPhone\": \"1122334455\", \"secondGuardianWechat\": \"jim_wechat\", \"healthState\": \"Healthy\", \"description\": \"Description\" }";

        mockMvc.perform(post("/oldperson")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(oldPersonJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(0)));
    }
}
