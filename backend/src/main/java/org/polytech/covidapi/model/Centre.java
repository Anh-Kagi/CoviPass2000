package org.polytech.covidapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Centre {
    @Id
    @Getter
    private Long id;

    @Getter
    @Setter
    private String nom;

    @Getter
    @Setter
    private String ville;

    public Centre(String nom, String ville) {
        this.nom = nom;
        this.ville = ville;
    }

    protected Centre() {
    }
}
