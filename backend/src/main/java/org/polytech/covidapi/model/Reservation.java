package org.polytech.covidapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Reservation {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @ManyToOne
    private Centre centre;

    @Getter
    @Setter
    @ManyToOne
    private Patient patient;

    @Getter
    @Setter
    private boolean faite;

    protected Reservation() {
    }

    public Reservation(Centre centre, Patient patient) {
        setCentre(centre);
        setPatient(patient);
        setFaite(false);
    }
}
