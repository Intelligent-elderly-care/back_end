package com.example.smartpensionsystem.controller;

import com.example.smartpensionsystem.entity.Event;
import com.example.smartpensionsystem.entity.Result;
import com.example.smartpensionsystem.service.EventService;
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
public class EventControllerTests {

    private MockMvc mockMvc;

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
    }

    @Test
    public void testGetEventById() throws Exception {
        Event event = new Event(1, 0, LocalDateTime.now(), "Location", "Description", 1);

        when(eventService.getEventById(1)).thenReturn(event);

        mockMvc.perform(get("/events/findById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data.event_desc").value("Description"))
                .andDo(print());
    }

    @Test
    public void testGetAllEvents() throws Exception {
        Event event = new Event(1, 0, LocalDateTime.now(), "Location", "Description", 1);
        List<Event> events = Arrays.asList(event);

        when(eventService.getAllEvents()).thenReturn(events);

        mockMvc.perform(get("/events/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data[0].event_desc").value("Description"))
                .andDo(print());
    }

    @Test
    public void testInsertEvent() throws Exception {
        Event event = new Event(1, 0, LocalDateTime.now(), "Location", "Description", 1);

        doNothing().when(eventService).insertEvent(any(Event.class));
        when(eventService.getEventById(1)).thenReturn(null);

        mockMvc.perform(put("/events/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"event_type\": 0, \"event_date\": \"2023-06-01T00:00:00\", \"event_location\": \"Location\", \"event_desc\": \"Description\", \"oldperson_id\": 1 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").value("操作成功"))
                .andDo(print());

        event.setEvent_desc(null);
        mockMvc.perform(put("/events/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"event_type\": 0, \"event_date\": \"2023-06-01T00:00:00\", \"event_location\": \"Location\", \"event_desc\": null, \"oldperson_id\": 1 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.message").value("描述不能为空"))
                .andDo(print());
    }

    @Test
    public void testDeleteEvent() throws Exception {
        Event event = new Event(1, 0, LocalDateTime.now(), "Location", "Description", 1);

        when(eventService.getEventById(1)).thenReturn(event);
        doNothing().when(eventService).deleteEvent(1);

        mockMvc.perform(delete("/events/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").value("操作成功"))
                .andDo(print());
    }

    @Test
    public void testUpdateEvent() throws Exception {
        Event event = new Event(1, 0, LocalDateTime.now(), "Location", "Description", 1);

        when(eventService.getEventById(1)).thenReturn(event);
        doNothing().when(eventService).updateEvent(any(Event.class));

        mockMvc.perform(post("/events/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"event_type\": 0, \"event_date\": \"2023-06-01T00:00:00\", \"event_location\": \"Location\", \"event_desc\": \"Updated Description\", \"oldperson_id\": 1 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").value("操作成功"))
                .andDo(print());
    }

    @Test
    public void testGetEventsByType() throws Exception {
        Event event1 = new Event(1, 0, LocalDateTime.now(), "Location1", "Description1", 1);
        Event event2 = new Event(2, 0, LocalDateTime.now(), "Location2", "Description2", 2);
        List<Event> events = Arrays.asList(event1, event2);

        when(eventService.getEventsByType("0")).thenReturn(events);

        mockMvc.perform(get("/events/findByType/0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data[0].event_desc").value("Description1"))
                .andExpect(jsonPath("$.data[1].event_desc").value("Description2"))
                .andDo(print());
    }
}
