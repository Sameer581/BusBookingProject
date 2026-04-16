package com.cg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.BusBooking;


@Repository
public interface BusBookingRepo extends JpaRepository<BusBooking, Long> {
	public List<BusBooking> findByCustomerId(Long customerId);

}
