package com.cg.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cg.entity.RouteSchedule;
import com.cg.repo.RouteScheduleRepo;

@RestController
@RequestMapping("/schedule")
public class BusScheduleController {

    @Autowired
    RouteScheduleRepo scheduleRepo;

    @PostMapping("/create")
    public RouteSchedule createSchedule(@RequestBody RouteSchedule schedule) {
        return scheduleRepo.save(schedule);
    }
}