package com.ticketapp.service;

import com.ticketapp.dto.request.AirlineRequest;
import com.ticketapp.dto.request.AirportRequest;
import com.ticketapp.dto.response.AirportResponse;
import com.ticketapp.entity.Airline;
import com.ticketapp.entity.Airport;

import java.util.List;

public interface AirportService {
    AirportResponse createAirport(AirportRequest airportRequest);
    AirportResponse updateAirport(AirportRequest airportRequest,Long id);
    List<AirportResponse> getAllAirport();
    AirportResponse getAirport(Long id);
    void deletAirport(Long id);
}
