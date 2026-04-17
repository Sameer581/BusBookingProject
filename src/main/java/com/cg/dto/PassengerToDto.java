package com.cg.dto;

import com.cg.entity.Passenger;

public class PassengerToDto {
	public static PassengerDto mapToDto(Passenger passenger) {
		return new PassengerDto(passenger.getPassengerName(), passenger.getPassengerAge(), passenger.getSeatNo(),
				passenger.getBooking().getId());
	}
}
