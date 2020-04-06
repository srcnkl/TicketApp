package com.ticketapp.dto.response;

import com.ticketapp.entity.Flight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FlightResponse {
    private String id;
    private String routeId;
    private String airlineId;
    private String airlineName;
    private String departureName;
    private String destinationName;
    private Date departureTime;
    private Date arrivalTime;
    private int seat;
    private Double price;

    public static FlightResponse from(Flight flight){
       return FlightResponse.builder()
                .id(flight.getId().toString())
                .price(flight.getUpdatedPrice())
                .routeId(flight.getRoute().getId().toString())
                .airlineId(flight.getAirline().getId().toString())
                .airlineName(flight.getAirline().getName())
                .departureName(flight.getRoute().getDeparture().getName())
                .destinationName(flight.getRoute().getDestination().getName())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .seat(flight.getAvailableSeat())
                .build();
    }
}
