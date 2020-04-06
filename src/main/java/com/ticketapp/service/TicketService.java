package com.ticketapp.service;

import com.ticketapp.dto.request.TicketRequest;
import com.ticketapp.dto.response.TicketResponse;

import java.util.List;

public interface TicketService {
    TicketResponse createTicket(TicketRequest ticketRequest);

    List<TicketResponse> getAllTicket();

    TicketResponse getTicketById(Long id);

    void cancelTicket(Long id);

}
