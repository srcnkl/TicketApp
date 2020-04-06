package com.ticketapp.service;

import com.ticketapp.dto.request.FlightCreateRequest;
import com.ticketapp.dto.response.FlightResponse;
import com.ticketapp.entity.Airport;
import com.ticketapp.entity.Flight;

import java.util.List;

public interface FlightService  {
    FlightResponse createFlight(FlightCreateRequest flightCreateRequest);
    List<FlightResponse> getAllFlight();
    FlightResponse getFlightById(Long id);
    List<FlightResponse> getFlightByRoute(Long routeId);
    List<FlightResponse> getFlightByAirlineId(Long airlineId);

}
