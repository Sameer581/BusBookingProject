package com.cg.dto;

import java.time.LocalDate;
import java.util.List;

import com.cg.entity.Passenger;

public record BusBookingDto(Long scheduleId, Integer custId, LocalDate bookingDt, List<Passenger> passengers) {

}
