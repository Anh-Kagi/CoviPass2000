package org.polytech.covidapi.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Patient {

    String nomPatient;
    String prenomPatient;
    String mail;
    long numeroTelephone;
    boolean vaccine;
    @Id
    private Long idPatient;

    public void estVaccine(boolean vaccine) {
        vaccine = vaccine;
    }

    @Id
    public Long getId() {
        return idPatient;
    }

    public void setId(Long id) {
        this.idPatient = id;
    }
}
