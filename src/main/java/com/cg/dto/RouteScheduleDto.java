package com.cg.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record RouteScheduleDto(
		Long scheduleId,
		LocalTime departureTime, 
		LocalDate scheduleDate, 
		int totalSeats, 
		int availableSeats,
		BusRouteDto route,
		List<String> bookedSeats) {

}
