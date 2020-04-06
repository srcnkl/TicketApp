package com.ticketapp.repository;

import com.ticketapp.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRouteRepository extends JpaRepository<Route,Long> {
}
