package com.ticketapp.dto.request;

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
public class FlightCreateRequest {
    private long airlineId;
    private long routeId;
    private Date departureTime;
    private Date arrivalTime;
    private int capacity;
    private Double price;
}
