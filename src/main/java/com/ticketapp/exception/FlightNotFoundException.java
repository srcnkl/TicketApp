package com.ticketapp.exception;

public class FlightNotFoundException extends  RuntimeException {
    public FlightNotFoundException(){
        super(ExceptionCode.FlightNotFoundException);
    }
}
