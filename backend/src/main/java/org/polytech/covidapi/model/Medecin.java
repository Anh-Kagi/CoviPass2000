package org.polytech.covidapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Medecin {
    @Id
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Centre centre;

    public Medecin(Centre centre) {
        setCentre(centre);
    }

    protected Medecin() {
    }

    public Long getId() {
        return id;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }
}
