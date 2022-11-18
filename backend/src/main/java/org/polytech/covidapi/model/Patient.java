package org.polytech.covidapi.model;



import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Patient {

    @Id
    private Long idPatient;
    String nomPatient;
    String prenomPatient;
    String mail;
    long numeroTelephone;
    boolean vaccine;


    public void estVaccine(boolean vaccine){
        vaccine = vaccine;
    }


    public void setId(Long id) {
        this.idPatient = id;
    }

    @Id
    public Long getId() {
        return idPatient;
    }
}
