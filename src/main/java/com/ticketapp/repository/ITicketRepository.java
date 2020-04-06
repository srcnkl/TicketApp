package com.ticketapp.repository;

import com.ticketapp.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITicketRepository extends JpaRepository<Ticket,Long> {

}
