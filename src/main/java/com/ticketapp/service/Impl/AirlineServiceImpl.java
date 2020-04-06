package com.ticketapp.service.Impl;

import com.ticketapp.dto.request.AirlineRequest;
import com.ticketapp.dto.response.AirlineResponse;
import com.ticketapp.entity.Airline;
import com.ticketapp.exception.AirlineNotFoundException;
import com.ticketapp.repository.IAirlineRepository;
import com.ticketapp.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private final IAirlineRepository iAirlineRepository;

    @Override
    public AirlineResponse createAirline(AirlineRequest airline) {
        Airline airline1 = new Airline();
        airline1.setName(airline.getName());
        Airline savedAirline = iAirlineRepository.save(airline1);
        return AirlineResponse.from(savedAirline);
    }

    @Override
    public AirlineResponse updateAirline(AirlineRequest airlineRequest, Long id) {
        Airline airlineUpdated = iAirlineRepository.findById(id).orElseThrow(AirlineNotFoundException::new);
        airlineUpdated.setName(airlineRequest.getName());
        Airline savedAirline=iAirlineRepository.save(airlineUpdated);
        return AirlineResponse.from(savedAirline);
    }

    @Override
    public List<AirlineResponse> getAllAirline() {
        List<Airline> airlinesList = iAirlineRepository.findAll();
        return airlinesList.stream().map(AirlineResponse::from).collect(Collectors.toList());
    }

    @Override
    public AirlineResponse getAirline(Long id) {
        Airline airline = iAirlineRepository.findById(id).orElseThrow(AirlineNotFoundException::new);
        return AirlineResponse.from(airline);
    }

    @Override
    public void deleteAirline(Long id) {
        Airline airlineDeleted = iAirlineRepository.findById(id).orElseThrow(AirlineNotFoundException::new);
        iAirlineRepository.delete(airlineDeleted);
    }

}
