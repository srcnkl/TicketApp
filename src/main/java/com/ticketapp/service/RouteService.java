package com.ticketapp.service;

import com.ticketapp.dto.request.RouteRequest;
import com.ticketapp.dto.response.RouteResponse;
import com.ticketapp.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteService {
    RouteResponse createRoute(RouteRequest routeRequest);
    List<RouteResponse> getAllRoute();
    RouteResponse getRoute(long id);
    void deleteRoute(long id);
}
