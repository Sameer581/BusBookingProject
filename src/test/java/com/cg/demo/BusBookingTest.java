package com.cg.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.cg.dto.BusBookingDto;
import com.cg.service.BusBookingService;

import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class BusBookingTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BusBookingService bookingService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void bookTicket_success() throws Exception {
        BusBookingDto input = new BusBookingDto(
                null, 10L, 1L, LocalDate.now(), null
        );
        BusBookingDto output = new BusBookingDto(
                101L, 10L, 1L, LocalDate.now(), null
        );

        when(bookingService.createBooking(any())).thenReturn(output);

        mockMvc.perform(post("/booking/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookingId").value(101));
    }

    @Test
    void bookTicket_validationFailure() throws Exception {
        BusBookingDto invalid = new BusBookingDto(
                null, null, null, LocalDate.now(), null
        );

        mockMvc.perform(post("/booking/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errormsg").value("validation failed"))
                .andExpect(jsonPath("$.errMap.scheduleId").exists())
                .andExpect(jsonPath("$.errMap.custId").exists());
    }

    @Test
    void getBookingsByCustomer_success() throws Exception {
        BusBookingDto dto = new BusBookingDto(
                1L, 10L, 1L, LocalDate.now(), null
        );

        when(bookingService.getBookingsByCustomer(1L)).thenReturn(List.of(dto));

        mockMvc.perform(get("/booking/customer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].bookingId").value(1));
    }
}