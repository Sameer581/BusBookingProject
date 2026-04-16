package com.cg.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class BusBooking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "booking_dt")
	private LocalDate bookingDate;

	@Column(name = "booking_status")
	private String bookingStatus;

	@ManyToOne
	@JoinColumn(name = "schedule_id")
	private RouteSchedule schedule;

	@ManyToOne
	@JoinColumn(name = "cust_id")
	private Customer customer;

	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
	private List<Passenger> passengers;

	public BusBooking() {
	}

	public BusBooking(Long id, LocalDate bookingDate, String bookingStatus, RouteSchedule schedule, Customer customer) {
		super();
		this.id = id;
		this.bookingDate = bookingDate;
		this.bookingStatus = bookingStatus;
		this.schedule = schedule;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public RouteSchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(RouteSchedule schedule) {
		this.schedule = schedule;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
}
