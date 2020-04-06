package com.ticketapp.service;

import com.ticketapp.dto.request.AirportRequest;
import com.ticketapp.dto.response.AirportResponse;
import com.ticketapp.entity.Airport;
import com.ticketapp.exception.AirportNotFoundException;
import com.ticketapp.repository.IAirportRepository;
import com.ticketapp.service.Impl.AirportServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AirportServiceTest {

    @Mock
    private IAirportRepository iAirportRepository;

    @InjectMocks
    private AirportServiceImpl airportService;
    private AirportRequest airportRequest;
    private Airport airport;

    @Before
    public void init() {
        airportRequest = AirportRequest.builder().location("test").name("test").build();
        airport = Airport.builder().id(1L).location("test").name("test").build();

    }

    @Test
    public void it_should_create_an_airport() {
        //given
        when(iAirportRepository.save(Matchers.any())).thenReturn(airport);
        //when
        AirportResponse airportResponse = airportService.createAirport(airportRequest);
        //then
        Assert.assertEquals("1", airportResponse.getId());
        Assert.assertEquals("test", airportResponse.getLocation());
        Assert.assertEquals("test", airportResponse.getName());
    }

    @Test
    public void it_should_return_an_airport_by_given_Id() {
        //given
        when(iAirportRepository.findById(1L)).thenReturn(Optional.of(airport));
        //when
        AirportResponse airportResponse = airportService.getAirport(1L);
        //then
        Assert.assertEquals("1", airportResponse.getId());
        Assert.assertEquals("test", airportResponse.getLocation());
        Assert.assertEquals("test", airportResponse.getName());

    }

    @Test
    public void it_should_be_updated_by_given_properties() {
        //given
        airportRequest = AirportRequest.builder().location("updated").name("updated").build();
        when(iAirportRepository.findById(1L)).thenReturn(Optional.of(airport));
        when(iAirportRepository.save(any())).thenReturn(airport);
        //when
        AirportResponse airportResponse = airportService.updateAirport(airportRequest, 1L);
        //then
        Assert.assertEquals("updated", airportResponse.getName());
        Assert.assertEquals("updated", airportResponse.getLocation());
        Assert.assertEquals("1", airportResponse.getId());
    }

    @Test(expected = AirportNotFoundException.class)
    public void it_should_throw_an_exception_when_id_not_exist() {
        //given
        when(iAirportRepository.findById(1L)).thenReturn(Optional.empty());
        //when
        AirportResponse airportResponse = airportService.getAirport(1L);
    }

    @Test(expected = AirportNotFoundException.class)
    public void it_should_be_deleted_by_given_airport_id() {
        //given
        airport = Airport.builder().id(1L).location("test").name("test").build();
        when(iAirportRepository.findById(1L)).thenReturn(Optional.of(airport)).thenReturn(Optional.empty());
        doNothing().when(iAirportRepository).delete(airport);
        //when
        airportService.deletAirport(1L);
        airportService.getAirport(1L);
    }


}
