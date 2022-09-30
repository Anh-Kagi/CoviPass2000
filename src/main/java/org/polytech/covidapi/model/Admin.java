package org.polytech.covidapi.model;

import javax.persistence.*;

@Entity
public class Admin {
    @Id
    private Long id;
    @OneToOne
    private Centre centre;

    public Admin(Centre centre) {
        setCentre(centre);
    }

    protected Admin() {}

    public Long getIdAdmin() {
        return id;
    }

    public void setIdAdmin(Long id) {
        this.id = id;
    }

    public Centre getCentre() {
        return this.centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }
}
