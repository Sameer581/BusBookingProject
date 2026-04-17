package com.cg.entity;

import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@DynamicUpdate
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cust_id")
	private Long customerId;

	@Column(name = "cust_name",  unique = true, nullable = false, length = 100)
	private String customerName;

	@Column(name = "cust_phone_no", nullable = false)
	private String customerPhoneNumber;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<BusBooking> bookings;

	public Customer() {
	}

	public Customer(Long customerId, String customerName, String customerPhoneNumber) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public List<BusBooking> getBookings() {
		return bookings;
	}

	public void setBookings(List<BusBooking> bookings) {
		this.bookings = bookings;
	}

}
