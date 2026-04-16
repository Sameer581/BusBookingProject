package com.cg.dto;

import com.cg.entity.BusBooking;

public class BookingToDto {
	public static BusBookingDto mapToDto(BusBooking booking) {
	    return new BusBookingDto(
	        booking.getSchedule().getId(),
	        booking.getCustomer().getCustomerId(),
	        booking.getBookingDate(),
	        booking.getPassengers()
	    );
	}
}