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
    private boolean faite;

    public Long getId() {
        return id;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setFaq(boolean faite) {
        this.faite = faite;
    }

    public boolean getFaite() {
        return faite;
    }
}
