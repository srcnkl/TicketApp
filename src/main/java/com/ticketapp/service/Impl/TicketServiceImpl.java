package com.ticketapp.service.Impl;

import com.google.common.base.Ticker;
import com.ticketapp.dto.request.TicketRequest;
import com.ticketapp.dto.response.AirportResponse;
import com.ticketapp.dto.response.TicketResponse;
import com.ticketapp.entity.Flight;
import com.ticketapp.entity.Ticket;
import com.ticketapp.exception.FlightNotFoundException;
import com.ticketapp.exception.TicketNotFoundException;
import com.ticketapp.exception.TimeoutException;
import com.ticketapp.repository.IFlightRepository;
import com.ticketapp.repository.ITicketRepository;
import com.ticketapp.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final ITicketRepository iTicketRepository;
    private final IFlightRepository iFlightRepository;

    @Transactional
    public TicketResponse createTicket(TicketRequest ticketRequest) {
        Flight flight = iFlightRepository.findById(ticketRequest.getFlightId()).orElseThrow(FlightNotFoundException::new);
        flight.addPassenger();
        iFlightRepository.save(flight);
        if (!flight.getUpdatedPrice().equals(ticketRequest.getPrice())) {
            throw new TimeoutException();
        }
        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setPrice(flight.getUpdatedPrice());
        Ticket savedTicket = iTicketRepository.save(ticket);
        return TicketResponse.from(savedTicket);
    }

    @Override
    public TicketResponse getTicketById(Long id) {
        Ticket ticket = iTicketRepository.findById(id).orElseThrow(TicketNotFoundException::new);
        return TicketResponse.from(ticket);
    }

    @Override
    public void cancelTicket(Long id) {
        Ticket ticket = iTicketRepository.findById(id).orElseThrow(TicketNotFoundException::new);
        Flight flight = iFlightRepository.findById(ticket.getFlight().getId()).orElseThrow(FlightNotFoundException::new);
        flight.removePassenger();
        iFlightRepository.save(flight);
        iTicketRepository.delete(ticket);
    }

    @Override
    public List<TicketResponse> getAllTicket() {
        List<Ticket> ticketList = iTicketRepository.findAll();
        return ticketList.stream().map(TicketResponse::from).collect(Collectors.toList());
    }
}
