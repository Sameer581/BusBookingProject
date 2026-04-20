package com.cg.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.SignupDto;
import com.cg.entity.Customer;
import com.cg.entity.Role;
import com.cg.entity.RolePk;
import com.cg.entity.User;
import com.cg.repo.CustomerRepo;
import com.cg.repo.RoleRepo;
import com.cg.repo.UserRepo;

import jakarta.transaction.Transactional;

@Transactional
@RestController
public class SignupController {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CustomerRepo custRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@PostMapping("/signup")
	public ResponseEntity<String> registerUser(@RequestBody SignupDto dto) {
		if(userRepo.findByUsername(dto.username()).isPresent()) {
			return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
		}
		
		if(custRepo.findByCustomerPhoneNumber(dto.phoneNo()).isPresent()) {
			return new ResponseEntity<>("Phone number already exists", HttpStatus.CONFLICT);
		}
		
		User user = new User();
		user.setUsername(dto.username());
		user.setPassword(passwordEncoder.encode(dto.password()));
		user.setEnabled(true);
		
		Customer cust = new Customer();
		cust.setCustomerName(dto.custName());
		cust.setCustomerPhoneNumber(dto.phoneNo());
		cust.setUser(user);
		
		RolePk rolePk = new RolePk();
		rolePk.setUserName(dto.username());
		rolePk.setRoleName("ROLE_USER");

		Role role = new Role();
		role.setKey(rolePk);

		roleRepo.save(role);
		
		userRepo.save(user);
		custRepo.save(cust);
		return new ResponseEntity<>("Signed Up Successful", HttpStatus.OK);
	}
	
}
