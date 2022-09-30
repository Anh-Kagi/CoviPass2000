package org.polytech.covidapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Medecin {

    @ManyToOne
    private Centre centre;
    private Long idMedecin;

    @Id
    public Long getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(Long idMedecin) {
        this.idMedecin = idMedecin;
    }
}
