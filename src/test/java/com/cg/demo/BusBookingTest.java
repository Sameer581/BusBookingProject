package com.cg.demo;

import com.cg.dto.BusBookingDto;
import com.cg.service.BusBookingService;
import com.cg.web.BusBookingController;
import com.cg.exception.GlobalExceptionHandler;
import com.cg.security.JWTAuthFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BusBookingController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)   // ✅ Important for validation response
class BusBookingTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BusBookingService bookingService;

    @MockBean
    private JWTAuthFilter jwtAuthFilter; // ✅ Prevents security bean error

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

        when(bookingService.createBooking(any()))
                .thenReturn(output);

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

        when(bookingService.getBookingsByCustomer(1L))
                .thenReturn(List.of(dto));

        mockMvc.perform(get("/booking/customer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].bookingId").value(1));
    }
}