package com.cg.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.BusBooking;
import com.cg.service.BusBookingService;

@RestController
@RequestMapping("/booking")
public class BusBookingController {
	
	@Autowired
	private BusBookingService busBookingService;
	
	@PostMapping("/book")
	@ResponseStatus(HttpStatus.CREATED)
	public 
	
	@GetMapping("/viewall")
	public BusBooking get

}
