package com.ticketapp.service.Impl;

import com.ticketapp.dto.request.RouteRequest;
import com.ticketapp.dto.response.FlightResponse;
import com.ticketapp.dto.response.RouteResponse;
import com.ticketapp.entity.Airport;
import com.ticketapp.entity.Route;
import com.ticketapp.exception.AirportNotFoundException;
import com.ticketapp.exception.RouteNotFoundException;
import com.ticketapp.repository.IAirportRepository;
import com.ticketapp.repository.IRouteRepository;
import com.ticketapp.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final IRouteRepository iRouteRepository;
    private final IAirportRepository iAirportRepository;

    @Override
    public RouteResponse createRoute(RouteRequest routeRequest) {
        Long departureId = routeRequest.getDepartureId();
        Long destinationId = routeRequest.getDestinationId();
        Airport airportDep = iAirportRepository.findById(departureId).orElseThrow(AirportNotFoundException::new);
        Airport airportDes = iAirportRepository.findById(destinationId).orElseThrow(AirportNotFoundException::new);
        Route route = Route.builder().departure(airportDep).destination(airportDes).build();
        Route savedRoute=iRouteRepository.save(route);
        return RouteResponse.from(savedRoute);
    }


    @Override
    public List<RouteResponse> getAllRoute() {
        List<Route> routeList = iRouteRepository.findAll();
        return routeList.stream().map(RouteResponse::from).collect(Collectors.toList());
    }

    @Override
    public RouteResponse getRoute(long id) {
        Route route = iRouteRepository.findById(id).orElseThrow(RouteNotFoundException::new);
        return RouteResponse.from(route);
    }

    @Override
    public void deleteRoute(long id) {
        Route route = iRouteRepository.findById(id).orElseThrow(RouteNotFoundException::new);
        iRouteRepository.delete(route);
    }
}
