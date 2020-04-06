package com.ticketapp.repository;

import com.ticketapp.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAirlineRepository extends JpaRepository<Airline,Long> {

}
