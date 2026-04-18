package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.BookingToDto;
import com.cg.dto.BusBookingDto;
import com.cg.dto.RouteScheduleDto;
import com.cg.dto.ScheduleToDto;
import com.cg.entity.BusBooking;
import com.cg.entity.BusRoute;
import com.cg.entity.Customer;
import com.cg.entity.Passenger;
import com.cg.entity.RouteSchedule;
import com.cg.exception.NotAvailableException;
import com.cg.repo.BusBookingRepo;
import com.cg.repo.BusRouteRepo;
import com.cg.repo.CustomerRepo;
import com.cg.repo.RouteScheduleRepo;

@Service
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
	public RouteScheduleDto createSchedule(RouteScheduleDto dto) {
		RouteSchedule schedule = new RouteSchedule();
		BusRoute route = routeRepo.findById(dto.route().routeId())
				.orElseThrow(() -> new NotAvailableException("bus route not found with id " + dto.route().routeId()));

		schedule.setAvailableSeats(dto.availableSeats());
		schedule.setDepartureTime(dto.departureTime());
		schedule.setScheduleDate(dto.scheduleDate());
		schedule.setRoute(route);
		schedule.setTotalSeats(dto.totalSeats());
		schedule.setScheduleStatus("SCHEDULED");

		return ScheduleToDto.mapToDto(scheduleRepo.save(schedule));
	}

	@Override
	public BusBookingDto createBooking(BusBookingDto dto) {
		BusBooking booking = new BusBooking();
		Customer cust = custRepo.findById(dto.custId())
				.orElseThrow(() -> new NotAvailableException("customer not found with id " + dto.custId()));
		RouteSchedule schedule = scheduleRepo.findById(dto.scheduleId())
				.orElseThrow(() -> new NotAvailableException("route schedule not found with id " + dto.scheduleId()));

		booking.setBookingDate(dto.bookingDt());
		booking.setCustomer(cust);
		booking.setSchedule(schedule);
		booking.setBookingStatus("BOOKED");

		List<Passenger> passengers = dto.passengers() != null ? dto.passengers().stream().map(p -> {
			Passenger passenger = new Passenger();
			passenger.setPassengerAge(p.passengerAge());
			passenger.setPassengerName(p.passengerName());
			passenger.setSeatNo(p.seatNo());
			passenger.setBooking(booking);

			return passenger;

		}).toList() : List.of();

		booking.setPassengers(passengers);

		return BookingToDto.mapToDto(bookingRepo.save(booking));
	}

	@Override
	public List<BusBookingDto> getBookingsByCustomer(Long custId) {
		Customer cust = custRepo.findById(custId)
				.orElseThrow(() -> new NotAvailableException("customer not found with id " + custId));

		List<BusBookingDto> bookings = cust.getBookings().stream().map(BookingToDto::mapToDto).toList();

		return bookings;
	}

	@Override
	public List<BusBookingDto> getAllBookings() {

		return null;
	}

	@Override
	public List<RouteScheduleDto> getSchedules() {
		List<RouteSchedule> schedules = scheduleRepo.findAll();

		List<RouteScheduleDto> scheduleDtos = schedules.stream().map(ScheduleToDto::mapToDto).toList();

		return scheduleDtos;
	}

	@Override
	public List<RouteScheduleDto> getSchedulesBySrcDestDate(String src, String dest, LocalDate scheduleDate) {
		List<RouteSchedule> schedules = scheduleRepo
				.findByRoute_SrcIgnoreCaseAndRoute_DestIgnoreCaseAndScheduleDate(src, dest, scheduleDate);

		List<RouteScheduleDto> scheduleDtos = schedules.stream().map(ScheduleToDto::mapToDto).toList();

		return scheduleDtos;
	}
}