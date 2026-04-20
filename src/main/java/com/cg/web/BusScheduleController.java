package com.cg.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.RouteScheduleDto;
import com.cg.exception.ValidationException;
import com.cg.service.BusBookingService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/schedule")
@CrossOrigin(origins = { "http://localhost:4200" })
@SecurityRequirement(name = "BearerAuth")
public class BusScheduleController {

	@Autowired
	private BusBookingService busService;

	@PostMapping("/create")
	public ResponseEntity<RouteScheduleDto> createSchedule(@Valid @RequestBody RouteScheduleDto schedule, BindingResult br) {
		
		if (br.hasErrors()) {
			throw new ValidationException(br.getFieldErrors());
		}
		
		return new ResponseEntity<>(busService.createSchedule(schedule), HttpStatus.CREATED);
	}

	@GetMapping("viewall")
	public ResponseEntity<List<RouteScheduleDto>> getSchedules() {
		return new ResponseEntity<>(busService.getSchedules(), HttpStatus.OK);
	}

	@GetMapping("view")
	public ResponseEntity<List<RouteScheduleDto>> getSchedulesBySrcAndDestAndDate(@RequestParam String src,
			@RequestParam String dest, @RequestParam LocalDate scheduleDate) {
		return new ResponseEntity<>(busService.getSchedulesBySrcDestDate(src, dest, scheduleDate), HttpStatus.OK);
	}
	
	@GetMapping("view/{id}")
	public ResponseEntity<RouteScheduleDto> getScheduleById(@PathVariable Long id) {
		return new ResponseEntity<>(busService.getScheduleById(id), HttpStatus.OK);
	}
}