package org.polytech.covidapi.model;

import javax.persistence.*;

@Entity
public class Medecin {

    @ManyToOne
    private Centre centre;
    private Long idMedecin;


    public void setIdMedecin(Long idMedecin) {
        this.idMedecin = idMedecin;
    }

    @Id
    public Long getIdMedecin() {
        return idMedecin;
    }
}
