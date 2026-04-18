package com.cg.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.RouteSchedule;

@Repository
public interface RouteScheduleRepo extends JpaRepository<RouteSchedule, Long> {
	public List<RouteSchedule> findByRoute_SrcIgnoreCaseAndRoute_DestIgnoreCaseAndScheduleDate(String src, String dest,
			LocalDate scheduleDate);
}
