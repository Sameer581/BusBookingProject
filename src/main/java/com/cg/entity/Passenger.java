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
	@JoinColumn(name = "booking_id")
	private BusBooking booking;

	// getters & setters
}
