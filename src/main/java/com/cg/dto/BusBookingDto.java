package com.cg.dto;

import java.time.LocalDate;
import java.util.List;


public record BusBookingDto(
		Long bookingId,
		Long scheduleId, 
		Long custId, 
		LocalDate bookingDt, 
		List<PassengerDto> passengers) {
	
}

