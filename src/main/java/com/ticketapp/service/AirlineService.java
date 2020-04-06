package com.ticketapp.service;

import com.ticketapp.dto.request.AirlineRequest;
import com.ticketapp.dto.response.AirlineResponse;
import com.ticketapp.entity.Airline;

import java.util.List;

public interface AirlineService {
    AirlineResponse createAirline(AirlineRequest airline);
    AirlineResponse updateAirline(AirlineRequest airline,Long id);
    List<AirlineResponse> getAllAirline();
    AirlineResponse getAirline(Long id);
    void deleteAirline(Long id);

}
