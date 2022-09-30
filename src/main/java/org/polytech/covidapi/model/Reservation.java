package org.polytech.covidapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {
    @Id
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Centre centre;
    @ManyToOne(cascade = CascadeType.ALL)
    private Patient patient;
    private boolean faite;

    public Reservation(Centre centre, Patient patient) {
        setCentre(centre);
        setPatient(patient);
        setFaite(false);
    }

    protected Reservation() {
    }

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

    public boolean getFaite() {
        return faite;
    }

    public void setFaite(boolean faite) {
        this.faite = faite;
    }
}
