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
                .andExpect(jsonPath("$.bookingId").value(101))
                .andExpect(jsonPath("$.scheduleId").value(10))
                .andExpect(jsonPath("$.custId").value(1));
    }

    @Test
    void bookTicket_validationFailure_nullScheduleAndCust() throws Exception {

        BusBookingDto invalid = new BusBookingDto(
                null, null, null, LocalDate.now(), null  // ❌ scheduleId & custId @NotNull
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
    void bookTicket_validationFailure_pastDate() throws Exception {

        BusBookingDto invalid = new BusBookingDto(
                null, 10L, 1L, LocalDate.now().minusDays(1), null  // ❌ @FutureOrPresent
        );

        mockMvc.perform(post("/booking/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errormsg").value("validation failed"))
                .andExpect(jsonPath("$.errMap.bookingDt").exists());
    }

    @Test
    void bookTicket_futureDate_success() throws Exception {

        BusBookingDto input = new BusBookingDto(
                null, 10L, 1L, LocalDate.now().plusDays(5), null
        );
        BusBookingDto output = new BusBookingDto(
                103L, 10L, 1L, LocalDate.now().plusDays(5), null
        );

        when(bookingService.createBooking(any())).thenReturn(output);

        mockMvc.perform(post("/booking/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookingId").value(103));
    }

    // ───────────────────────────────────────────
    // GET /booking/customer/{id}
    // ───────────────────────────────────────────

    @Test
    void getBookingsByCustomer_success() throws Exception {

        BusBookingDto dto = new BusBookingDto(
                1L, 10L, 1L, LocalDate.now(), null
        );

        when(bookingService.getBookingsByCustomer(1L)).thenReturn(List.of(dto));

        mockMvc.perform(get("/booking/customer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].bookingId").value(1))
                .andExpect(jsonPath("$[0].scheduleId").value(10))
                .andExpect(jsonPath("$[0].custId").value(1));
    }

    @Test
    void getBookingsByCustomer_emptyList() throws Exception {

        when(bookingService.getBookingsByCustomer(99L)).thenReturn(List.of());

        mockMvc.perform(get("/booking/customer/99"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));
    }

    @Test
    void getBookingsByCustomer_multipleBookings() throws Exception {

        BusBookingDto dto1 = new BusBookingDto(1L, 10L, 2L, LocalDate.now(), null);
        BusBookingDto dto2 = new BusBookingDto(2L, 20L, 2L, LocalDate.now().plusDays(1), null);

        when(bookingService.getBookingsByCustomer(2L)).thenReturn(List.of(dto1, dto2));

        mockMvc.perform(get("/booking/customer/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].bookingId").value(1))
                .andExpect(jsonPath("$[1].bookingId").value(2));
    }
}