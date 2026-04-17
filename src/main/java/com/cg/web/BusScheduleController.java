package com.cg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.RouteScheduleDto;
import com.cg.service.BusBookingService;

@RestController
@RequestMapping("/schedule")
@CrossOrigin(origins = { "http://localhost:4200" })
public class BusScheduleController {

	@Autowired
	private BusBookingService busService;

	@PostMapping("/create")
	public ResponseEntity<RouteScheduleDto> createSchedule(@RequestBody RouteScheduleDto schedule) {
		return new ResponseEntity<>(busService.createSchedule(schedule), HttpStatus.CREATED);
	}

	@GetMapping("viewall")
	public ResponseEntity<List<RouteScheduleDto>> getSchedules() {
		return new ResponseEntity<>(busService.getSchedules(), HttpStatus.OK);
	}
}