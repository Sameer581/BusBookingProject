package com.cg.service;

import java.util.List;

import com.cg.dto.BusBookingDto;
import com.cg.dto.RouteScheduleDto;
import com.cg.entity.BusBooking;
import com.cg.entity.RouteSchedule;

public interface BusBookingService {

	public RouteSchedule createSchedule(RouteScheduleDto dto);

	public BusBooking createBooking(BusBookingDto dto);

	public List<BusBookingDto> getAllBookings();

	public List<BusBookingDto> getBookingsByCustomer(Long custId);

}