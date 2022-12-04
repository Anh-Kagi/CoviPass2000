package org.polytech.covidapi.model;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Centre centre;
    @ManyToOne
    private Patient patient;
    private boolean faite;

    protected Reservation() {}
    
    public Reservation(Centre centre, Patient patient) {
        setCentre(centre);
        setPatient(patient);
        setFaite(false);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
