package com.ticketapp.controller;

import com.ticketapp.dto.request.TicketRequest;
import com.ticketapp.dto.response.TicketResponse;
import com.ticketapp.service.TicketService;
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
@RequestMapping("ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponse> buyTicket(@RequestBody TicketRequest ticketRequest) {
        return new ResponseEntity<>(ticketService.createTicket(ticketRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicket(@PathVariable Long id) {
        return new ResponseEntity<>(ticketService.getTicketById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> getAllTicket() {
        return new ResponseEntity<>(ticketService.getAllTicket(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public HttpStatus cancelTicket(@PathVariable Long id) {
        ticketService.cancelTicket(id);
        return HttpStatus.OK;
    }
}
