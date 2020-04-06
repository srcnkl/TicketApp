package com.ticketapp.service.Impl;

import com.ticketapp.dto.request.FlightCreateRequest;
import com.ticketapp.dto.response.FlightResponse;
import com.ticketapp.entity.Airline;
import com.ticketapp.entity.Flight;
import com.ticketapp.entity.Route;
import com.ticketapp.exception.AirportNotFoundException;
import com.ticketapp.exception.FlightNotFoundException;
import com.ticketapp.exception.RouteNotFoundException;
import com.ticketapp.repository.IAirlineRepository;
import com.ticketapp.repository.IFlightRepository;
import com.ticketapp.repository.IRouteRepository;
import com.ticketapp.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final IFlightRepository iFlightRepository;
    private final IAirlineRepository iAirlineRepository;
    private final IRouteRepository iRouteRepository;


    //get byID , getByAirlineId, getByRootID
    @Override
    public FlightResponse createFlight(FlightCreateRequest flightCreateRequest) {
        Long airlineId = flightCreateRequest.getAirlineId();
        Long routeId = flightCreateRequest.getRouteId();
        Airline airline = iAirlineRepository.findById(airlineId).orElseThrow(AirportNotFoundException::new);
        Route route = iRouteRepository.findById(routeId).orElseThrow(RouteNotFoundException::new);
        Flight flight = Flight.builder()
                .airline(airline)
                .route(route)
                .arrivalTime(flightCreateRequest.getArrivalTime())
                .departureTime(flightCreateRequest.getDepartureTime())
                .capacity(flightCreateRequest.getCapacity())
                .price(flightCreateRequest.getPrice())
                .build();
        Flight savedFlight=iFlightRepository.save(flight);
        return FlightResponse.from(savedFlight);
    }

    @Override
    public List<FlightResponse> getAllFlight() {
        List<Flight> flightsList = iFlightRepository.findAll();
        return flightsList.stream().map(FlightResponse::from).collect(Collectors.toList());
    }

    @Override
    public FlightResponse getFlightById(Long id) {
        Flight flight=iFlightRepository.findById(id).orElseThrow(FlightNotFoundException::new);
        return FlightResponse.from(flight);
    }

    @Override
    public List<FlightResponse> getFlightByRoute(Long routeId) {
        List<Flight> flightsList = iFlightRepository.findByRouteId(routeId);
        return flightsList.stream().map(FlightResponse::from).collect(Collectors.toList());
    }

    @Override
    public List<FlightResponse> getFlightByAirlineId(Long airlineId) {
        List<Flight> flightsList =iFlightRepository.findByAirlineId(airlineId);
        return flightsList.stream().map(FlightResponse::from).collect(Collectors.toList());
    }
}
