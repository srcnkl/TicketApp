package com.ticketapp.dto.response;

import com.ticketapp.entity.Airline;
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
public class AirlineResponse {

    private String id;
    private String name;

    public static AirlineResponse from(Airline airline){
         return AirlineResponse.builder()
                        .id(airline.getId().toString())
                        .name(airline.getName())
                        .build();
    }
}
