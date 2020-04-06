package com.ticketapp.controller;

import com.ticketapp.dto.request.AirlineRequest;
import com.ticketapp.dto.response.AirlineResponse;
import com.ticketapp.service.AirlineService;
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
@RequestMapping("/airline")
@RequiredArgsConstructor
public class AirlineController {

    private final AirlineService airlineService;

    @PostMapping
    public ResponseEntity<AirlineResponse> addAirline(@RequestBody AirlineRequest airlineRequest) {
        return new ResponseEntity<>(airlineService.createAirline(airlineRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineResponse> getAirline(@PathVariable Long id) {
        return new ResponseEntity<>(airlineService.getAirline(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteAirline(@PathVariable Long id) {
        airlineService.deleteAirline(id);
        return HttpStatus.OK;
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirlineResponse> updateAirline(@RequestBody AirlineRequest airlineRequest, @PathVariable Long id) {
        return new ResponseEntity<>(airlineService.updateAirline(airlineRequest, id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AirlineResponse>> getAllAirline() {
        return new ResponseEntity<>(airlineService.getAllAirline(), HttpStatus.OK);
    }


}
