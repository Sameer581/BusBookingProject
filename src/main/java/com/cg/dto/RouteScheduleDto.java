package com.cg.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record RouteScheduleDto(
		Long scheduleId,
		
		@FutureOrPresent
		LocalTime departureTime,
		
		@FutureOrPresent
		LocalDate scheduleDate, 
		
		@Min(10)
		@Max(100)
		int totalSeats, 
		
		@Min(0)
		@Max(100)
		int availableSeats,
		BusRouteDto route,
		List<String> bookedSeats) {

}
