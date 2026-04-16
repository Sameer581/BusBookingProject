package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.dto.BookingToDto;
import com.cg.dto.BusBookingDto;
import com.cg.dto.RouteScheduleDto;
import com.cg.entity.BusBooking;
import com.cg.entity.BusRoute;
import com.cg.entity.Customer;
import com.cg.entity.RouteSchedule;
import com.cg.exception.NotAvailableException;
import com.cg.repo.BusBookingRepo;
import com.cg.repo.BusRouteRepo;
import com.cg.repo.CustomerRepo;
import com.cg.repo.RouteScheduleRepo;

public class BusBookingServiceImple implements BusBookingService {

	@Autowired
	private RouteScheduleRepo scheduleRepo;

	@Autowired
	private BusRouteRepo routeRepo;

	@Autowired
	private CustomerRepo custRepo;

	@Autowired
	private BusBookingRepo bookingRepo;

	@Override
	public RouteSchedule createSchedule(RouteScheduleDto dto) {
		RouteSchedule schedule = new RouteSchedule();
		BusRoute route = routeRepo.findById(dto.routeId())
				.orElseThrow(() -> new NotAvailableException("bus route not found with id " + dto.routeId()));

		schedule.setAvailableSeats(dto.availableSeats());
		schedule.setDepartureTime(dto.departureTime());
		schedule.setScheduleDate(dto.scheduleDate());
		schedule.setRoute(route);
		schedule.setTotalSeats(dto.totalSeats());
		schedule.setScheduleStatus("SCHEDULED");

		return scheduleRepo.save(schedule);
	}

	@Override
	public BusBooking createBooking(BusBookingDto dto) {

		BusBooking booking = new BusBooking();
		Customer cust = custRepo.findById(dto.custId())
				.orElseThrow(() -> new NotAvailableException("customer not found with id " + dto.custId()));
		RouteSchedule schedule = scheduleRepo.findById(dto.scheduleId())
				.orElseThrow(() -> new NotAvailableException("route schedule not found with id " + dto.scheduleId()));

		booking.setBookingDate(dto.bookingDt());
		booking.setCustomer(cust);
		booking.setSchedule(schedule);
		booking.setBookingStatus("BOOKED");
		return bookingRepo.save(booking);
	}

	@Override
	public List<BusBookingDto> getBookingsByCustomer(Long custId) {

		Customer cust = custRepo.findById(custId)
				.orElseThrow(() -> new NotAvailableException("customer not found with id " + custId));

		List<BusBookingDto> bookings = cust.getBookings().stream().map(BookingToDto::mapToDto).toList();

		return bookings;
	}
}
