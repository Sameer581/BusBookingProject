package com.cg.dto;

import com.cg.entity.BusRoute;
import com.cg.entity.RouteSchedule;

public class ScheduleToDto {
	public static RouteScheduleDto mapToDto(RouteSchedule schedule) {
		BusRoute route = schedule.getRoute();
		BusRouteDto dto = new BusRouteDto(route.getRouteId(), route.getSrc(), route.getDest());
		return new RouteScheduleDto(schedule.getDepartureTime(), schedule.getScheduleDate(), schedule.getTotalSeats(),
				schedule.getAvailableSeats(), dto);

	}
}
