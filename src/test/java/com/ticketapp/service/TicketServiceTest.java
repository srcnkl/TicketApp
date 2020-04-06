package com.ticketapp.service;


import com.ticketapp.dto.request.TicketRequest;
import com.ticketapp.dto.response.TicketResponse;
import com.ticketapp.entity.Airline;
import com.ticketapp.entity.Airport;
import com.ticketapp.entity.Flight;
import com.ticketapp.entity.Route;
import com.ticketapp.entity.Ticket;
import com.ticketapp.exception.TimeoutException;
import com.ticketapp.repository.IFlightRepository;
import com.ticketapp.repository.ITicketRepository;
import com.ticketapp.service.Impl.TicketServiceImpl;
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
public class TicketServiceTest {

    @Mock
    private ITicketRepository iTicketRepository;
    @Mock
    private IFlightRepository iFlightRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;
    private Flight flight;
    private Airline airline;
    private Airport airportDep, airportDes;
    private Route route;
    private TicketRequest ticketRequest;
    private Ticket ticket;

    @Before
    public void init() {
        airline = Airline.builder().id(1L).name("test").build();
        airportDep = Airport.builder().id(1L).location("test").name("test").build();
        airportDes = Airport.builder().id(1L).location("test").name("test").build();
        route = Route.builder().departure(airportDep).destination(airportDes).id(1L).build();
        flight = Flight.builder().capacity(100).id(1L).updatedPrice(200.0).price(200.0).passengerCount(2).route(route).airline(airline).build();
        ticketRequest = TicketRequest.builder().flightId(1L).price(200.0).build();
        ticket = Ticket.builder().id(1L).flight(flight).price(200.0).build();
    }

    @Test
    public void it_should_create_a_ticket() {

        BDDMockito.given(iFlightRepository.findById(1L)).willReturn(Optional.of(flight));
        BDDMockito.given(iFlightRepository.save(flight)).willReturn(flight);
        BDDMockito.given(iTicketRepository.save(Matchers.any())).willReturn(ticket);
        //when
        TicketResponse ticketResponse = ticketService.createTicket(ticketRequest);
        //then
        Assert.assertEquals(ticketResponse.getDeparture(), ticket.getFlight().getRoute().getDeparture().getName());
        Assert.assertEquals(ticketResponse.getPrice(), ticket.getPrice());
        Assert.assertEquals(ticketResponse.getFlightID(), ticket.getFlight().getId().toString());
        Assert.assertEquals(ticketResponse.getDestination(), ticket.getFlight().getRoute().getDestination().getName());
    }

    @Test
    public void ticket_price_should_be_increase_10_percent_when_10_percent_of_ticket_sold() {
        //given
        flight = Flight.builder().capacity(100).id(1L).updatedPrice(100.0).price(100.0).passengerCount(10).route(route).airline(airline).build();
        TicketRequest ticketRequest = TicketRequest.builder().flightId(1L).price(110.0).build();
        Ticket ticket = Ticket.builder().id(1L).flight(flight).price(110.0).build();

        //then
        BDDMockito.given(iFlightRepository.findById(1L)).willReturn(Optional.of(flight));
        BDDMockito.given(iFlightRepository.save(flight)).willReturn(flight);
        BDDMockito.given(iTicketRepository.save(Matchers.any())).willReturn(ticket);

        TicketResponse ticketResponse = ticketService.createTicket(ticketRequest);

        Assert.assertEquals(110, 0, ticketResponse.getPrice());
        Assert.assertEquals(ticket.getId().toString(), ticketResponse.getId());
    }

    @Test
    public void ticket_price_should_be_increase_20_percent_when_20_percent_of_ticket_sold() {
        //given
        flight = Flight.builder().capacity(100).id(1L).updatedPrice(100.0).price(100.0).passengerCount(20).route(route).airline(airline).build();
        TicketRequest ticketRequest = TicketRequest.builder().flightId(1L).price(120.0).build();
        Ticket ticket = Ticket.builder().id(1L).flight(flight).price(120.0).build();

        //then
        BDDMockito.given(iFlightRepository.findById(1L)).willReturn(Optional.of(flight));
        BDDMockito.given(iFlightRepository.save(flight)).willReturn(flight);
        BDDMockito.given(iTicketRepository.save(Matchers.any())).willReturn(ticket);

        TicketResponse ticketResponse = ticketService.createTicket(ticketRequest);

        Assert.assertEquals(120, 0, ticketResponse.getPrice());
        Assert.assertEquals(ticket.getId().toString(), ticketResponse.getId());
    }


    @Test
    public void passenger_count_should_be_decrease_when_ticket_is_cancel() {
        //given
        BDDMockito.given(iFlightRepository.findById(1L)).willReturn(Optional.of(flight));
        BDDMockito.given(iTicketRepository.findById(1L)).willReturn(Optional.of(ticket));
        //when
        ticketService.cancelTicket(1L);
        //then
        Assert.assertEquals(1, flight.getPassengerCount());

    }

    @Test(expected = TimeoutException.class)
    public void should_throw_TimeOutException_when_ticket_flight_change_in_background() {
        flight = Flight.builder().capacity(100).id(1L).updatedPrice(200.0).price(200.0).passengerCount(20).route(route).airline(airline).build();

        BDDMockito.given(iFlightRepository.findById(1L)).willReturn(Optional.of(flight));
        BDDMockito.given(iFlightRepository.save(flight)).willReturn(flight);
        BDDMockito.given(iTicketRepository.save(Matchers.any())).willReturn(ticket);
        //when
        TicketResponse ticketResponse = ticketService.createTicket(ticketRequest);
    }

}
