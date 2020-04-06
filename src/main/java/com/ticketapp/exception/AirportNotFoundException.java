package com.ticketapp.exception;

public class AirportNotFoundException extends RuntimeException {
    public AirportNotFoundException(){
        super(ExceptionCode.AirportNotFoundException);
    }
}
