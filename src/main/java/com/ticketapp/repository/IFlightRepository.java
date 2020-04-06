package com.ticketapp.repository;

import com.ticketapp.entity.Airport;
import com.ticketapp.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IFlightRepository extends JpaRepository<Flight,Long> {
    List<Flight> findByRouteId(Long routeId);
    List<Flight> findByAirlineId(Long airlineId);
}
