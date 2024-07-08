package com.example.smartpensionsystem.controller;

import com.example.smartpensionsystem.entity.Result;
import com.example.smartpensionsystem.entity.Volunteer;
import com.example.smartpensionsystem.service.VolunteerService;
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
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class VolunteerControllerTests {

    private MockMvc mockMvc;

    @Mock
    private VolunteerService volunteerService;

    @InjectMocks
    private VolunteerController volunteerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(volunteerController).build();
    }

    @Test
    public void testGetVolunteerById() throws Exception {
        Volunteer volunteer = new Volunteer(1, "Jane Doe", "Female", "1234567890", "123456789012345678",
                LocalDateTime.of(1950, 1, 1, 0, 0), LocalDateTime.now(), LocalDateTime.now(),
                "http://example.com/image.jpg", "Description", 1);

        when(volunteerService.getVolunteerById(1)).thenReturn(volunteer);

        mockMvc.perform(get("/volunteers/findById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data.name").value("Jane Doe"))
                .andDo(print());
    }

    @Test
    public void testGetAllVolunteers() throws Exception {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer(1, "Jane Doe", "Female", "1234567890", "123456789012345678",
                LocalDateTime.of(1950, 1, 1, 0, 0), LocalDateTime.now(), LocalDateTime.now(),
                "http://example.com/image.jpg", "Description", 1));

        when(volunteerService.getAllVolunteers()).thenReturn(volunteers);

        mockMvc.perform(get("/volunteers/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data[0].name").value("Jane Doe"))
                .andDo(print());
    }

    @Test
    public void testInsertVolunteer() throws Exception {
        Volunteer volunteer = new Volunteer(null, "Jane Doe", "Female", "1234567890", "123456789012345678",
                LocalDateTime.of(1950, 1, 1, 0, 0), LocalDateTime.now(), LocalDateTime.now(),
                "http://example.com/image.jpg", "Description", 1);

        doNothing().when(volunteerService).insertVolunteer(any(Volunteer.class));

        mockMvc.perform(put("/volunteers/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Jane Doe\", \"gender\": \"Female\", \"phone\": \"1234567890\", \"id_card\": \"123456789012345678\", \"birthday\": \"1950-01-01T00:00:00\", \"checkin_date\": \"2023-06-01T00:00:00\", \"checkout_date\": \"2023-06-10T00:00:00\", \"imgset_dir\": \"http://example.com/image.jpg\", \"description\": \"Description\", \"oldperson_id\": 1 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andDo(print());
    }

    @Test
    public void testUpdateVolunteer() throws Exception {
        Volunteer volunteer = new Volunteer(1, "Jane Doe", "Female", "1234567890", "123456789012345678",
                LocalDateTime.of(1950, 1, 1, 0, 0), LocalDateTime.now(), LocalDateTime.now(),
                "http://example.com/image.jpg", "Description", 1);

        when(volunteerService.getVolunteerById(1)).thenReturn(volunteer);
        doNothing().when(volunteerService).updateVolunteer(any(Volunteer.class));

        mockMvc.perform(post("/volunteers/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"name\": \"Jane Doe Updated\", \"gender\": \"Female\", \"phone\": \"1234567890\", \"id_card\": \"123456789012345678\", \"birthday\": \"1950-01-01T00:00:00\", \"checkin_date\": \"2023-06-01T00:00:00\", \"checkout_date\": \"2023-06-10T00:00:00\", \"imgset_dir\": \"http://example.com/image.jpg\", \"description\": \"Description Updated\", \"oldperson_id\": 1 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andDo(print());
    }

    @Test
    public void testDeleteVolunteer() throws Exception {
        Volunteer volunteer = new Volunteer(1, "Jane Doe", "Female", "1234567890", "123456789012345678",
                LocalDateTime.of(1950, 1, 1, 0, 0), LocalDateTime.now(), LocalDateTime.now(),
                "http://example.com/image.jpg", "Description", 1);

        when(volunteerService.getVolunteerById(1)).thenReturn(volunteer);
        doNothing().when(volunteerService).deleteVolunteer(1);

        mockMvc.perform(delete("/volunteers/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andDo(print());
    }

    @Test
    public void testGetVolunteersByName() throws Exception {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer(1, "Jane Doe", "Female", "1234567890", "123456789012345678",
                LocalDateTime.of(1950, 1, 1, 0, 0), LocalDateTime.now(), LocalDateTime.now(),
                "http://example.com/image.jpg", "Description", 1));
        volunteers.add(new Volunteer(2, "John Doe", "Male", "0987654321", "876543210987654321",
                LocalDateTime.of(1960, 2, 2, 0, 0), LocalDateTime.now(), LocalDateTime.now(),
                "http://example.com/image2.jpg", "Description2", 2));

        when(volunteerService.getVolunteersByName("Jane Doe")).thenReturn(volunteers);

        mockMvc.perform(get("/volunteers/findByName")
                        .param("name", "Jane Doe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data[0].name").value("Jane Doe"))
                .andExpect(jsonPath("$.data[1].name").value("John Doe"))
                .andDo(print());
    }
}
