package com.ticketapp.exception;

public class AirlineNotFoundException extends RuntimeException {
        public AirlineNotFoundException(){
            super(ExceptionCode.AirlineNotFoundException);
        }
}
