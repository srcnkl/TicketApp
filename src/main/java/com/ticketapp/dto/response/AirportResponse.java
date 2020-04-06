package com.ticketapp.dto.response;

import com.ticketapp.entity.Airport;
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
public class AirportResponse {
    private String id;
    private String name;
    private String location;

    public static AirportResponse from(Airport airport){
        return AirportResponse.builder()
                            .name(airport.getName())
                            .location(airport.getLocation())
                            .id(airport.getId().toString())
                            .build();
    }
}
