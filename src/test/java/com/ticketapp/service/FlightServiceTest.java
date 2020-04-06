package com.ticketapp.service;

import com.ticketapp.dto.request.FlightCreateRequest;
import com.ticketapp.dto.response.FlightResponse;
import com.ticketapp.entity.Airline;
import com.ticketapp.entity.Airport;
import com.ticketapp.entity.Flight;
import com.ticketapp.entity.Route;
import com.ticketapp.exception.FlightNotFoundException;
import com.ticketapp.repository.IAirlineRepository;
import com.ticketapp.repository.IFlightRepository;
import com.ticketapp.repository.IRouteRepository;
import com.ticketapp.service.Impl.FlightServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FlightServiceTest {
    @Mock
    private IFlightRepository iFlightRepository;
    @Mock
    private IAirlineRepository iAirlineRepository;
    @Mock
    private IRouteRepository iRouteRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    private Airline airline;
    private Airport airportDep, airportDes;
    private Route route;
    private Flight flight;
    private List<Flight> flightList;

    @Before
    public void init() {
        airline = Airline.builder().id(1L).name("test").build();
        airportDep = Airport.builder().id(1L).location("test").name("test").build();
        airportDes = Airport.builder().id(1L).location("test").name("test").build();
        route = Route.builder().departure(airportDep).destination(airportDes).id(1L).build();
        flight = Flight.builder().capacity(100).id(1L).price(200.0).passengerCount(100).route(route).airline(airline).build();
        flightList = new ArrayList<>();
        flightList.add(flight);
    }

    @Test
    public void it_should_create_a_flight() {
        //given
        FlightCreateRequest flightCreateRequest = FlightCreateRequest.builder().price(200.0).capacity(100).airlineId(1L).routeId(1L).build();
        when(iAirlineRepository.findById(1L)).thenReturn(Optional.of(airline));
        when(iRouteRepository.findById(1L)).thenReturn(Optional.of(route));
        when(iFlightRepository.save(any())).thenReturn(flight);
        //when
        FlightResponse flightResponse = flightService.createFlight(flightCreateRequest);
        //then
        Assert.assertEquals("1", flightResponse.getAirlineId());
        Assert.assertEquals(flight.getPrice().toString(), flightResponse.getPrice().toString());
        Assert.assertEquals("test", flightResponse.getDepartureName());

    }

    @Test(expected = FlightNotFoundException.class)
    public void it_should_throw_an_exception_when_id_not_exist() {
        //given
        when(iFlightRepository.findById(1L)).thenReturn(Optional.empty());
        //when
        FlightResponse flightResponse = flightService.getFlightById(1L);
    }

    @Test
    public void it_should_return_a_flight_by_given_routeId() {
        //given
        when(iFlightRepository.findByRouteId(1L)).thenReturn(flightList);
        //when
        List<FlightResponse> flightResponse = flightService.getFlightByRoute(1L);
        //then
        Assert.assertEquals(1, flightResponse.size());
        Assert.assertEquals(flightList.get(0).getRoute().getId().toString(), flightResponse.get(0).getRouteId());
    }


}
