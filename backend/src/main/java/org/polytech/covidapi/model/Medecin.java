package org.polytech.covidapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Medecin extends BaseUser {
    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    private Centre centre;

    public Medecin(Centre centre) {
        setCentre(centre);
    }

    protected Medecin() {
    }
}
