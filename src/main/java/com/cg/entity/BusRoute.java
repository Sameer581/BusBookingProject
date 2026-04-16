package com.cg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bus_route")
public class BusRoute {
	@Id
	@Column(name = "route_id")
	private Integer routeId;
	
	@Column(name = "src")
	private String src;
	
	@Column(name = "dest")
	private String dest;
	
	
}
