package com.ticketapp.controller;

import com.ticketapp.dto.request.AirportRequest;
import com.ticketapp.dto.response.AirportResponse;
import com.ticketapp.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/airport")
@RequiredArgsConstructor
public class AirportController {
    private final AirportService airportService;

    @PostMapping
    public ResponseEntity<AirportResponse> addAirport(@RequestBody AirportRequest airportRequest) {
        return new ResponseEntity<>(airportService.createAirport(airportRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportResponse> getAirport(@PathVariable Long id) {
        return new ResponseEntity<>(airportService.getAirport(id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteAirport(@PathVariable Long id) {
        airportService.deletAirport(id);
        return HttpStatus.OK;
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportResponse> updateAirport(@RequestBody AirportRequest airportRequestst, @PathVariable Long id) {
        return new ResponseEntity<>(airportService.updateAirport(airportRequestst, id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AirportResponse>> getAllAirport() {
        return new ResponseEntity<>(airportService.getAllAirport(), HttpStatus.OK);
    }
}
