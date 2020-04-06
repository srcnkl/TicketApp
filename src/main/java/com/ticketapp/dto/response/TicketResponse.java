package com.ticketapp.dto.response;

import com.ticketapp.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponse {
    private String id;
    private String flightID;
    private Double price;
    private String departure;
    private String destination;
    private Date departureTime;
    private Date arrivalTime;

    public static TicketResponse from(Ticket ticket){
        return TicketResponse.builder()
                .id(ticket.getId().toString())
                .flightID(ticket.getFlight().getId().toString())
                .departure(ticket.getFlight().getRoute().getDeparture().getName())
                .destination(ticket.getFlight().getRoute().getDestination().getName())
                .price(ticket.getPrice())
                .arrivalTime(ticket.getFlight().getArrivalTime())
                .departureTime(ticket.getFlight().getDepartureTime())
                .build();
    }
}
