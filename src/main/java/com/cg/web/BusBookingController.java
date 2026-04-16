package com.cg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.cg.dto.BusBookingDto;
import com.cg.entity.BusBooking;
import com.cg.service.BusBookingService;

@RestController
@RequestMapping("/booking")
public class BusBookingController {

    @Autowired
    private BusBookingService bookingService;

    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    public BusBooking bookTicket(@RequestBody BusBookingDto booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping("/customer/{id}")
    public List<BusBookingDto> getBookingsByCustomer(@PathVariable Long id) {
        return bookingService.getBookingsByCustomer(id);
    }
}