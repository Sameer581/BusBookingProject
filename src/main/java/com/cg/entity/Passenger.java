package com.cg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "seat_no", "schedule_id" }))
public class Passenger {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "passender_id")
	private Long id;

	@Column(name = "passenger_name")
	private String passengerName;

	@Column(name = "passenger_age")
	private int passengerAge;

	@Column(name = "seat_no")
	private int seatNo;
	
	@ManyToOne
	@JoinColumn(name = "schedule_id")
	private RouteSchedule schedule;

	@ManyToOne
	@JoinColumn(name = "booking_id")
	private BusBooking booking;

	// getters & setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public int getPassengerAge() {
		return passengerAge;
	}

	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public BusBooking getBooking() {
		return booking;
	}

	public void setBooking(BusBooking booking) {
		this.booking = booking;
	}

	public Passenger(Long id, String passengerName, int passengerAge, int seatNo, BusBooking booking) {
		super();
		this.id = id;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.seatNo = seatNo;
		this.booking = booking;
	}

	public Passenger() {

	}

}
