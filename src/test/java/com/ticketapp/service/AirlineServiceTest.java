package com.ticketapp.service;

import com.ticketapp.dto.request.AirlineRequest;
import com.ticketapp.dto.response.AirlineResponse;
import com.ticketapp.entity.Airline;
import com.ticketapp.exception.AirlineNotFoundException;
import com.ticketapp.repository.IAirlineRepository;
import com.ticketapp.service.Impl.AirlineServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RestClientTest(AirlineServiceTest.class)
@RunWith(MockitoJUnitRunner.class)
public class AirlineServiceTest {
    @Mock
    private IAirlineRepository airlineRepository;
    @InjectMocks
    private AirlineServiceImpl airlineService;

    private Airline airline;
    private Airline updatedAirline;
    private AirlineRequest airlineRequest;

    @Before
    public void init() {
        airline = Airline.builder().id(1L).name("test").build();
        airlineRequest = AirlineRequest.builder().name("test").build();
    }

    @Test
    public void it_should_create_an_airline() {
        //given
        when(airlineRepository.save(any())).thenReturn(airline);
        //when
        AirlineResponse airlineResponse = airlineService.createAirline(airlineRequest);
        //then
        Assert.assertEquals("1", airlineResponse.getId());
        Assert.assertEquals("test", airlineResponse.getName());
    }

    @Test
    public void it_should_be_updated_by_given_properties() {
        //given
        airlineRequest = AirlineRequest.builder().name("updated").build();
        when(airlineRepository.findById(1L)).thenReturn(Optional.of(airline));
        when(airlineRepository.save(any())).thenReturn(airline);
        //when
        AirlineResponse airlineResponse = airlineService.updateAirline(airlineRequest, 1L);
        //then
        Assert.assertEquals("updated", airlineResponse.getName());
        Assert.assertEquals("1", airlineResponse.getId());
    }

    @Test
    public void it_should_return_an_airline_by_given_Id() {
        //given
        when(airlineRepository.findById(1L)).thenReturn(Optional.of(airline));
        //when
        AirlineResponse airlineResponse = airlineService.getAirline(1L);
        //then
        Assert.assertEquals("1", airlineResponse.getId());
        Assert.assertEquals("test", airlineResponse.getName());
    }

    @Test(expected = AirlineNotFoundException.class)
    public void it_should_throw_an_exception_when_id_not_exist() {
        //given
        when(airlineRepository.findById(1L)).thenReturn(Optional.empty());
        //when
        AirlineResponse airlineResponse = airlineService.getAirline(1L);
        //then
    }

    @Test(expected = AirlineNotFoundException.class)
    public void it_should_be_deleted_by_given_airport_id() {
        //given
        when(airlineRepository.findById(1L)).thenReturn(Optional.of(airline)).thenReturn(Optional.empty());
        //when
        airlineService.deleteAirline(1L);
        airlineService.getAirline(1L);
    }
}
