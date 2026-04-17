package com.cg.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record RouteScheduleDto(
		LocalTime departureTime, 
		LocalDate scheduleDate, 
		int totalSeats, 
		int availableSeats,
		BusRouteDto route) {

}
