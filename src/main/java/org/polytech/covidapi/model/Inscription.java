package org.polytech.covidapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Inscription {
    private Long idInscription;
    @ManyToOne
    private Centre centre;
    @ManyToOne
    private Patient patient;

    @Id
    public Long getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(Long idInscription) {
        this.idInscription = idInscription;
    }
}
