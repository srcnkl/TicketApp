package com.ticketapp.service.Impl;

import com.ticketapp.dto.request.AirportRequest;
import com.ticketapp.dto.response.AirportResponse;
import com.ticketapp.entity.Airport;
import com.ticketapp.exception.AirportNotFoundException;
import com.ticketapp.repository.IAirportRepository;
import com.ticketapp.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {
    private final IAirportRepository iAirportRepository;

    @Override
    public AirportResponse createAirport(AirportRequest airportRequest) {
        Airport airport = new Airport();
        airport.setName(airportRequest.getName());
        airport.setLocation(airportRequest.getLocation());
        Airport savedAirport = iAirportRepository.save(airport);
        return AirportResponse.from(savedAirport);
    }

    @Override
    public AirportResponse updateAirport(AirportRequest airportRequest, Long id) {
        Airport airportUpdated = iAirportRepository.findById(id).orElseThrow(AirportNotFoundException::new);
        airportUpdated.setName(airportRequest.getName());
        airportUpdated.setLocation(airportRequest.getLocation());
        Airport savedAirport = iAirportRepository.save(airportUpdated);
        return AirportResponse.from(savedAirport);
    }

    @Override
    public List<AirportResponse> getAllAirport() {
        List<Airport> airportsList = iAirportRepository.findAll();
        return airportsList.stream().map(AirportResponse::from).collect(Collectors.toList());
    }

    @Override
    public AirportResponse getAirport(Long id) {
        Airport airport = iAirportRepository.findById(id).orElseThrow(AirportNotFoundException::new);
        return AirportResponse.from(airport);
    }

    @Override
    public void deletAirport(Long id) {
        Airport airportDeleted = iAirportRepository.findById(id).orElseThrow(AirportNotFoundException::new);
        iAirportRepository.delete(airportDeleted);
    }
}
