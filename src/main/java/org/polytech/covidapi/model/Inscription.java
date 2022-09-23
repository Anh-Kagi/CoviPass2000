package org.polytech.covidapi.model;

import javax.persistence.*;

@Entity
public class Inscription {
    private Long idInscription;
    @ManyToOne
    private Centre centre;
    @ManyToOne
    private Patient patient;

    public void setIdInscription(Long idInscription) {
        this.idInscription = idInscription;
    }

    @Id
    public Long getIdInscription() {
        return idInscription;
    }
}
