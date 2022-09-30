package org.polytech.covidapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Admin {
    @Id
    private Long id;
    @OneToOne
    private Centre centre;

    public Admin(Centre centre) {
        setCentre(centre);
    }

    protected Admin() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Centre getCentre() {
        return this.centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }
}
