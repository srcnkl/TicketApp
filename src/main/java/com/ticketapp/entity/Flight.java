package com.ticketapp.entity;

import com.sun.org.apache.bcel.internal.generic.DUP;
import com.ticketapp.exception.CapacityLimitException;
import com.ticketapp.exception.FlightNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import java.util.Date;


@Entity
@Table(name="flight")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Flight {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @Column(nullable = false)
    private Date departureTime;

    @Column(nullable = false)
    private Date arrivalTime;

    @Column(nullable = false)
    private int capacity;

    private int passengerCount=0;

    @Column(nullable = false)
    private Double price;

    @Transient
    private Double updatedPrice;

    @Version
    private Long version=0L;

    public Double getUpdatedPrice() {
        Double ratio=(double)(passengerCount*100)/capacity;
        return calculatePrice(ratio);
    }

    private Double calculatePrice(Double ratio) {
        updatedPrice=price;
        if (10<=ratio && ratio<20){
            updatedPrice= updatedPrice+updatedPrice*0.1;
        }
        else if (20<=ratio && ratio<30){
            updatedPrice= updatedPrice+updatedPrice*0.2;
        }
        else if (30<=ratio && ratio<40){
            updatedPrice= updatedPrice+updatedPrice*0.3;
        }
        return updatedPrice;
    }

    public void addPassenger(){
        passengerCount++;
        if (passengerCount>capacity){
            throw new CapacityLimitException();
        }
    }

    public  void removePassenger(){
        passengerCount--;
        getUpdatedPrice();
    }

    public void UpdatedPrice() {
        Double ratio=(double)(passengerCount*100)/capacity;
        calculatePrice(ratio);
    }

    public int getAvailableSeat(){
        return capacity-passengerCount;
    }

}
