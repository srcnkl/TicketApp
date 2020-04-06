package com.ticketapp.repository;

import com.ticketapp.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAirportRepository extends JpaRepository<Airport,Long> {

}
