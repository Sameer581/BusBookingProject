package com.cg.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.cg.dto.RouteScheduleDto;
import com.cg.service.BusBookingService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class BusScheduleTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BusBookingService busService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    void CreateSuccess() throws Exception {

        RouteScheduleDto dto = new RouteScheduleDto(
                1L,
                LocalTime.now().plusHours(1),
                LocalDate.now().plusDays(1),
                50,
                40,
                null,
                List.of("A1", "A2")
        );
        Mockito.when(busService.createSchedule(Mockito.any()))
                .thenReturn(dto);

        mockMvc.perform(post("/schedule/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.scheduleId").value(1));
    }
}