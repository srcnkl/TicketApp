package com.ticketapp.controller;

import com.ticketapp.dto.request.FlightCreateRequest;
import com.ticketapp.dto.response.FlightResponse;
import com.ticketapp.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flight")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<FlightResponse> addFlight(@RequestBody FlightCreateRequest flightRequest) {
        return new ResponseEntity<>(flightService.createFlight(flightRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponse> getFlight(@PathVariable Long id) {
        return new ResponseEntity<>(flightService.getFlightById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FlightResponse>> getAllFlight() {
        return new ResponseEntity<>(flightService.getAllFlight(), HttpStatus.OK);
    }

    @GetMapping("/route/{routeId}")
    public ResponseEntity<List<FlightResponse>> getAllFlightByRouteId(@PathVariable Long routeId) {
        return new ResponseEntity<>(flightService.getFlightByRoute(routeId), HttpStatus.OK);
    }

    @GetMapping("/airline/{airlineId}")
    public ResponseEntity<List<FlightResponse>> getAllFlightByAirlineId(@PathVariable Long airlineId) {
        return new ResponseEntity<>(flightService.getFlightByAirlineId(airlineId), HttpStatus.OK);
    }
}
