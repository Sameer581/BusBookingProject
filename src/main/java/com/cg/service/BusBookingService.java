package com.cg.service;

import java.util.List;

import com.cg.dto.BusBookingDto;
import com.cg.dto.RouteScheduleDto;

public interface BusBookingService {

    public RouteScheduleDto createSchedule(RouteScheduleDto dto);
    public BusBookingDto createBooking(BusBookingDto dto);
    public List<BusBookingDto> getAllBookings();
    public List<BusBookingDto> getBookingsByCustomer(Long custId);
}