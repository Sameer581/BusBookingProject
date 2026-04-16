package com.cg.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
	private Set<RouteSchedule> routeSchedules;
	
}
