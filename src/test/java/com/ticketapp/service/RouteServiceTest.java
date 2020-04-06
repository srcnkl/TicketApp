package com.ticketapp.service;

import com.ticketapp.dto.request.RouteRequest;
import com.ticketapp.dto.response.RouteResponse;
import com.ticketapp.entity.Airport;
import com.ticketapp.entity.Route;
import com.ticketapp.exception.RouteNotFoundException;
import com.ticketapp.repository.IAirportRepository;
import com.ticketapp.repository.IRouteRepository;
import com.ticketapp.service.Impl.RouteServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class RouteServiceTest {
    @Mock
    private IRouteRepository routeRepository;
    @Mock
    private IAirportRepository airportRepository;
    @InjectMocks
    private RouteServiceImpl routeService;

    private Airport departureAirport;
    private Airport destinationAirport;
    private Route route;
    private RouteRequest routeRequest;

    @Before
    public void init(){
        departureAirport=Airport.builder().id(1L).location("test").name("test").build();
        destinationAirport=Airport.builder().id(1L).location("test").name("test").build();
        destinationAirport=Airport.builder().id(1L).location("test").name("test").build();
        route=Route.builder().id(1L).destination(destinationAirport).departure(departureAirport).build();
        routeRequest=RouteRequest.builder().departureId(departureAirport.getId()).destinationId(destinationAirport.getId()).build();
    }

    @Test
    public void  it_should_create_a_route(){
        //given
        BDDMockito.given(routeRepository.save(Matchers.any())).willReturn(route);
        BDDMockito.given(airportRepository.findById(1L)).willReturn(Optional.of(departureAirport)).willReturn(Optional.of(destinationAirport));//when
        //then
        RouteResponse routeResponse=routeService.createRoute(routeRequest);
        //then
        Assert.assertEquals("1",routeResponse.getId());
        Assert.assertEquals("test",routeResponse.getDepartureAirport());
        Assert.assertEquals("test",routeResponse.getDestinationAirport());
    }

    @Test
    public void  it_should_return_airport_by_given_id(){
        //given
        BDDMockito.given(routeRepository.findById(1L)).willReturn(Optional.of(route));
        //then
        RouteResponse routeResponse=routeService.getRoute(1L);
        //then
        Assert.assertEquals("1",routeResponse.getId());
        Assert.assertEquals("test",routeResponse.getDepartureAirport());
        Assert.assertEquals("test",routeResponse.getDestinationAirport());
    }
    @Test(expected = RouteNotFoundException.class)
    public void it_should_throw_an_exception_when_id_not_exist(){
        //given
        BDDMockito.given(routeRepository.findById(1L)).willReturn(Optional.empty());
        //then
        RouteResponse routeResponse=routeService.getRoute(1L);
    }

    @Test(expected = RouteNotFoundException.class)
    public void it_should_deleted_by_given_id(){
        //given
        BDDMockito.given(routeRepository.findById(1L)).willReturn(Optional.of(route)).willReturn(Optional.empty());
        //then
        routeService.deleteRoute(1L);
        routeService.getRoute(1L);
    }
}
