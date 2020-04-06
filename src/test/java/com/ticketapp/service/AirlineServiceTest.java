package com.ticketapp.service;

import com.ticketapp.dto.request.AirlineRequest;
import com.ticketapp.dto.response.AirlineResponse;
import com.ticketapp.dto.response.AirportResponse;
import com.ticketapp.entity.Airline;
import com.ticketapp.entity.Airport;
import com.ticketapp.exception.AirlineNotFoundException;
import com.ticketapp.exception.AirportNotFoundException;
import com.ticketapp.repository.IAirlineRepository;
import com.ticketapp.repository.IAirportRepository;
import com.ticketapp.service.Impl.AirlineServiceImpl;
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
public class AirlineServiceTest {
    @Mock
    private IAirlineRepository airlineRepository;
   @InjectMocks
    private AirlineServiceImpl airlineService;

   private Airline airline;
   private Airline updatedAirline;
   private AirlineRequest airlineRequest;

    @Before
    public void init(){
        airline = Airline.builder().id(1L).name("test").build();
        airlineRequest=AirlineRequest.builder().name("test").build();
    }

    @Test
    public void it_should_create_an_airline() {
        //given
        BDDMockito.given(airlineRepository.save(Matchers.any())).willReturn(airline);
        //when
        AirlineResponse airlineResponse=airlineService.createAirline(airlineRequest);
        //then
        Assert.assertEquals("1",airlineResponse.getId());
        Assert.assertEquals("test",airlineResponse.getName());
    }
    @Test
    public void it_should_be_updated_by_given_properties() {
        //given
        airlineRequest=AirlineRequest.builder().name("updated").build();
        BDDMockito.given(airlineRepository.findById(1L)).willReturn(Optional.of(airline));
        BDDMockito.given(airlineRepository.save(Matchers.any())).willReturn(airline);
        //when
        AirlineResponse airlineResponse=airlineService.updateAirline(airlineRequest,1L);
        //then
        Assert.assertEquals("updated",airlineResponse.getName());
        Assert.assertEquals("1",airlineResponse.getId());
    }
    @Test
    public void it_should_return_an_airline_by_given_Id() {
        //given
        BDDMockito.given(airlineRepository.findById(1L)).willReturn(Optional.of(airline));
        //when
        AirlineResponse airlineResponse=airlineService.getAirline(1L);
        //then
        Assert.assertEquals("1",airlineResponse.getId());
        Assert.assertEquals("test",airlineResponse.getName());
    }

    @Test(expected = AirlineNotFoundException.class)
    public void it_should_throw_an_exception_when_id_not_exist() {
        //given
        BDDMockito.given(airlineRepository.findById(1L)).willReturn(Optional.empty());
        //when
        AirlineResponse airlineResponse=airlineService.getAirline(1L);
        //then
    }

    @Test(expected = AirlineNotFoundException.class)
    public void it_should_be_deleted_by_given_airport_id(){
        //given
        BDDMockito.given(airlineRepository.findById(1L)).willReturn(Optional.of(airline)).willReturn(Optional.empty());
        //when
        airlineService.deleteAirline(1L);
        airlineService.getAirline(1L);
        //then
        //AirlineNotFoundException.class
    }
}
