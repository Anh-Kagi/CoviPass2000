package org.polytech.covidapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Centre {
    private Long idCentre;
    public String villeCentre;

    public void setIdCentre(Long idCentre) {
        this.idCentre = idCentre;
    }

    @Id
    public Long getIdCentre() {
        return idCentre;
    }
}
