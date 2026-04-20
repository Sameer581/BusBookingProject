package com.cg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Customer;


@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
	public Optional<Customer> findByCustomerPhoneNumber(String customerPhoneNumber);
	public Optional<Customer> findByUserUsername(String username);
}
