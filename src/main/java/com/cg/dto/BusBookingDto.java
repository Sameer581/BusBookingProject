package com.cg.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public record BusBookingDto(
		Long bookingId,
		
		@NotNull
		Long scheduleId, 
		
		@NotNull
		Long custId, 
		
		@FutureOrPresent
		LocalDate bookingDt, 
		List<PassengerDto> passengers) {
	
}

