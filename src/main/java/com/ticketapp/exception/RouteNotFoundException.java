package com.ticketapp.exception;

public class RouteNotFoundException extends RuntimeException{
    public RouteNotFoundException(){
        super(ExceptionCode.RouteNotFoundException);
    }
}
