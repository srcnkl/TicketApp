package com.ticketapp.exception;

public class CapacityLimitException extends RuntimeException {
    public CapacityLimitException(){
        super(ExceptionCode.CapacityLimitIsExceeded);
    }
}
