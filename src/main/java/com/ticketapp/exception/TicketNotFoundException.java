package com.ticketapp.exception;

public class TicketNotFoundException extends  RuntimeException{
    public TicketNotFoundException(){
        super(ExceptionCode.TicketNotFoundException);
    }
}
