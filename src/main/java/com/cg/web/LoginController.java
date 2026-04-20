package com.cg.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.LoginResponseDto;
import com.cg.entity.Customer;
import com.cg.repo.CustomerRepo;
import com.cg.security.AuthRequest;
import com.cg.security.JWTService;

@RestController
public class LoginController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private CustomerRepo custRepo;

	@PostMapping("/generateToken")
	public LoginResponseDto authenticateAndGetToken(@RequestBody AuthRequest authRequest) {

		Authentication authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			List<String> roles = authentication.getAuthorities().stream()

					.map(GrantedAuthority::getAuthority).toList();
			String token = jwtService.generateToken(authRequest.getUsername(), roles);

			LoginResponseDto dto = new LoginResponseDto();
			dto.setToken(token);
			dto.setMsg("user authenticated");
			dto.setUserName(authRequest.getUsername());
			dto.setTimestamp(LocalDateTime.now().toString());
			Customer cust = custRepo.findByUserUsername(authRequest.getUsername())
					.orElseThrow(() -> new RuntimeException("Customer not found"));

			dto.setCustId(cust.getCustomerId());

			return dto;
		} else {
			throw new UsernameNotFoundException("Invalid user request!");
		}
	}
}
