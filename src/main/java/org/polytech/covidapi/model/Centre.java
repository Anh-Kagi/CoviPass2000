package org.polytech.covidapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Centre {
    @Id
    private Long id;

    private String nom;
    private String ville;

    public Centre(String nom, String ville) {
        this.nom = nom;
        this.ville = ville;
    }

    protected Centre() {
    }

    public Long getId() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return this.ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
