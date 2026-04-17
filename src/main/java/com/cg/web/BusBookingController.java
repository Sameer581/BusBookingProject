package com.cg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cg.dto.BusBookingDto;
import com.cg.entity.BusBooking;
import com.cg.service.BusBookingService;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = {"http://localhost:4200"})
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