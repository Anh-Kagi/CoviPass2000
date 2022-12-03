package org.polytech.covidapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Admin {
    @Id
    private Long id;

    private String username;
    private String hash;

    @OneToOne(cascade = CascadeType.ALL)
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return this.hash;
    }
}
