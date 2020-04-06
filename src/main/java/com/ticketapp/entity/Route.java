package com.ticketapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity(name = "Route")
@Table(name="route")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Route {

    @Id
    @GeneratedValue
    private Long id;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "dep_airport_id")
   private Airport departure;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "des_airport_id")
   private Airport destination;

   @OneToMany(mappedBy = "route")
   private Set<Flight> flight;

}
