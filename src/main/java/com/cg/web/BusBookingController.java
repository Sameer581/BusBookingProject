package com.cg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.BusBookingDto;
import com.cg.service.BusBookingService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = {"http://localhost:4200"})
@SecurityRequirement(name = "BearerAuth")
public class BusBookingController {

	@Autowired
	private BusBookingService bookingService;

	@PostMapping("/book")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<BusBookingDto> bookTicket(@RequestBody BusBookingDto booking) {
		return new ResponseEntity<>(bookingService.createBooking(booking), HttpStatus.CREATED);
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<List<BusBookingDto>> getBookingsByCustomer(@PathVariable Long id) {
		return new ResponseEntity<>(bookingService.getBookingsByCustomer(id), HttpStatus.OK);
	}
}