package com.cg.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.BusRoute;


@Repository
public interface BusRouteRepo extends JpaRepository<BusRoute, Long> {

}
