package com.ticketapp.controller;

import com.ticketapp.dto.request.RouteRequest;
import com.ticketapp.dto.response.RouteResponse;
import com.ticketapp.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/route")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

    @PostMapping
    public ResponseEntity<RouteResponse> addRoute(@RequestBody RouteRequest routeRequest) {
        return new ResponseEntity<>(routeService.createRoute(routeRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteResponse> getRoute(@PathVariable Long id) {
        return new ResponseEntity<>(routeService.getRoute(id), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public HttpStatus deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return HttpStatus.OK;
    }

    @GetMapping
    public ResponseEntity<List<RouteResponse>> getAllRoute() {
        return new ResponseEntity<>(routeService.getAllRoute(), HttpStatus.OK);
    }
}
