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
	private Long routeId;

	@Column(name = "src")
	private String src;

	@Column(name = "dest")
	private String dest;

	@OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
	private Set<RouteSchedule> routeSchedules;

	public BusRoute() {
	}

	public BusRoute(Long routeId, String src, String dest) {
		super();
		this.routeId = routeId;
		this.src = src;
		this.dest = dest;
	}

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public Set<RouteSchedule> getRouteSchedules() {
		return routeSchedules;
	}

	public void setRouteSchedules(Set<RouteSchedule> routeSchedules) {
		this.routeSchedules = routeSchedules;
	}

}
