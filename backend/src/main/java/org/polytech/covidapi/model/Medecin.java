package org.polytech.covidapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Medecin {
    @Id
    @Getter
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @Getter
    @Setter
    private Centre centre;

    public Medecin(Centre centre) {
        setCentre(centre);
    }

    protected Medecin() {
    }
}
