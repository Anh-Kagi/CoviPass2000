package org.polytech.covidapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {
    @Id
    private Long id;
    @ManyToOne
    private Centre centre;
    @ManyToOne
    private Patient patient;

    public Long getId() {
        return id;
    }
}
