package com.cg.demo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dto.BusRouteDto;
import com.cg.dto.RouteScheduleDto;
import com.cg.entity.BusRoute;
import com.cg.entity.RouteSchedule;
import com.cg.exception.NotAvailableException;
import com.cg.repo.BusRouteRepo;
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
				.thenAnswer(invocation -> invocation.getArgument(0));

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
}
