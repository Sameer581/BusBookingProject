package com.cg.demo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dto.BusBookingDto;
import com.cg.dto.BusRouteDto;
import com.cg.dto.PassengerDto;
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
import com.cg.service.BusBookingService;
import com.cg.service.BusBookingServiceImple;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BusBookingServiceTests {
	@Mock
	private BusRouteRepo routeRepo;

	@Mock
	private RouteScheduleRepo scheduleRepo;

	@Mock
	private CustomerRepo custRepo;
	
	@Mock
	private BusBookingRepo bookingRepo;

	@InjectMocks
	private BusBookingService service = new BusBookingServiceImple();

	@Test
	void testCreateScheduleSuccess() {
		BusRoute route = new BusRoute();
		route.setRouteId(Long.valueOf(1));

		RouteScheduleDto dto = new RouteScheduleDto(null, LocalTime.of(10, 30), LocalDate.now(), 20, 40,
				new BusRouteDto(Long.valueOf(1), "kolkata", "mumbai"), new ArrayList<>());

		Mockito.when(routeRepo.findById(Long.valueOf(1))).thenReturn(Optional.of(route));

		Mockito.when(scheduleRepo.save(Mockito.any(RouteSchedule.class)))
				.thenAnswer(i -> i.getArgument(0));

		RouteScheduleDto result = service.createSchedule(dto);

		Assertions.assertNotNull(result);
		Mockito.verify(routeRepo).findById(Long.valueOf(1));
		Mockito.verify(scheduleRepo, Mockito.times(1)).save(Mockito.any());
	}

	@Test
	void testCreateScheduleFailure() {
		RouteScheduleDto dto = new RouteScheduleDto(null, LocalTime.of(10, 30), LocalDate.now(), 20, 40,
				new BusRouteDto(Long.valueOf(1), "kolkata", "mumbai"), new ArrayList<>());

		Mockito.when(routeRepo.findById(Long.valueOf(1))).thenReturn(Optional.empty());

		Assertions.assertThrows(NotAvailableException.class, () -> {
			service.createSchedule(dto);
		});
		Mockito.verify(routeRepo).findById(Long.valueOf(1));
	}

	@Test
	void testBookingSuccess() {
		Customer cust = new Customer();
		cust.setCustomerId(1L);
		
		RouteSchedule schedule = new RouteSchedule();
		schedule.setId(1L);
		schedule.setAvailableSeats(10);
		schedule.setBookedSeats(new ArrayList<>());
		
		PassengerDto passengers = new PassengerDto(1L, "tom", 22, "a1", null);
		
		BusBookingDto dto = new BusBookingDto(null, 1L, 1L, LocalDate.now(), List.of(passengers));
		
		Mockito.when(custRepo.findById(1L)).thenReturn(Optional.of(cust));
		Mockito.when(scheduleRepo.findById(1L)).thenReturn(Optional.of(schedule));
		Mockito.when(scheduleRepo.save(Mockito.any())).thenAnswer(i -> i.getArgument(0));
		Mockito.when(bookingRepo.save(Mockito.any())).thenAnswer(i -> i.getArgument(0));
		
		BusBookingDto result = service.createBooking(dto);
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(9, schedule.getAvailableSeats());
		Assertions.assertEquals(1, schedule.getBookedSeats().size());
		Assertions.assertEquals("A1", schedule.getBookedSeats().get(0));
		
		Mockito.verify(custRepo).findById(1L);
		Mockito.verify(scheduleRepo).findById(1L);
		Mockito.verify(scheduleRepo).save(Mockito.any());
		Mockito.verify(bookingRepo).save(Mockito.any());
	}
	
	@Test
	void testBookingCustomerFailure() {
		
		RouteSchedule schedule = new RouteSchedule();
		schedule.setId(1L);
		schedule.setAvailableSeats(10);
		schedule.setBookedSeats(new ArrayList<>());
		
		PassengerDto passengers = new PassengerDto(1L, "tom", 22, "a1", null);
		
		BusBookingDto dto = new BusBookingDto(null, 1L, 1L, LocalDate.now(), List.of(passengers));
		
		Mockito.when(custRepo.findById(1L)).thenReturn(Optional.empty());
		
		Assertions.assertThrows(NotAvailableException.class, () -> {
			service.createBooking(dto);
		});
		
		Mockito.verify(custRepo).findById(1L);
	}
	
	@Test
	void testBookingScheduleFailure() {
		Customer cust = new Customer();
		cust.setCustomerId(1L);
		
		PassengerDto passengers = new PassengerDto(1L, "tom", 22, "a1", null);
		
		BusBookingDto dto = new BusBookingDto(null, 1L, 1L, LocalDate.now(), List.of(passengers));
		
		Mockito.when(custRepo.findById(1L)).thenReturn(Optional.of(cust));
		Mockito.when(scheduleRepo.findById(1L)).thenReturn(Optional.empty());
		
		Assertions.assertThrows(NotAvailableException.class, () -> {
			service.createBooking(dto);
		});
		
		Mockito.verify(custRepo).findById(1L);
		Mockito.verify(scheduleRepo).findById(1L);
	}
}
