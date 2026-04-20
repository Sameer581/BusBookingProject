package com.cg.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class RouteSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "departure_time")
	private LocalTime departureTime;

	@Column(name = "schedule_date")
	private LocalDate scheduleDate;

	@Column(name = "total_seats")
	private int totalSeats;

	@Column(name = "available_seats")
	private int availableSeats;

	@Column(name = "schedule_status")
	private String scheduleStatus;
	
	@ElementCollection
	@CollectionTable(
	    name = "schedule_booked_seats",
	    joinColumns = @JoinColumn(name = "schedule_id")
	)
	@Column(name = "seat_no")
	private List<String> bookedSeats = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "route_id")
	private BusRoute route;

	@OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
	private List<BusBooking> bookings;

	public RouteSchedule() {
	}

	public RouteSchedule(Long id, LocalTime departureTime, LocalDate scheduleDate, int totalSeats, int availableSeats,
			String scheduleStatus, BusRoute route) {
		super();
		this.id = id;
		this.departureTime = departureTime;
		this.scheduleDate = scheduleDate;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.scheduleStatus = scheduleStatus;
		this.route = route;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDate getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(LocalDate scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public BusRoute getRoute() {
		return route;
	}

	public void setRoute(BusRoute route) {
		this.route = route;
	}

	public List<BusBooking> getBookings() {
		return bookings;
	}

	public void setBookings(List<BusBooking> bookings) {
		this.bookings = bookings;
	}
	
	public List<String> getBookedSeats() {
		return this.bookedSeats;
	}
	
	public void setBookedSeats(List<String> bookedSeats) {
		this.bookedSeats = bookedSeats;
	}
} 
