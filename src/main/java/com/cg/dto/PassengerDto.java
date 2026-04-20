package com.cg.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PassengerDto(
		Long passengerId, 
		
		@NotNull
		@Size(min = 3, message = "passenger name must be at least 3 characters long")
		@Size(max = 25, message = "passenger name must be at most 25 characters long")
		String passengerName, 
		
		@Min(1)
		int passengerAge, 
		
		@Size(min = 2, message = "seat number cannot be less than 2 characters long")
		@Size(max = 10, message = "seat no cannot be more than 10 characters long")
		String seatNo, 
	
		Long bookingId) {

}
