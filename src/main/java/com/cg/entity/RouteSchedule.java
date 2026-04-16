package com.cg.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class RouteSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime departureTime;
    private LocalDate scheduleDate;

    private int totalSeats;
    private int availableSeats;

    private String scheduleStatus;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private BusRoute route;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<BusBooking> bookings;

    // getters & setters
}
