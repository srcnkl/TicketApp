package com.ticketapp.dto.response;


import com.ticketapp.entity.Route;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RouteResponse {
    private String id;
    private String departureAirport;
    private String destinationAirport;

    public static RouteResponse from(Route route){
        return RouteResponse.builder()
                            .departureAirport(route.getDeparture().getName())
                            .destinationAirport(route.getDestination().getName())
                            .id(route.getId().toString())
                            .build();

    }
}
