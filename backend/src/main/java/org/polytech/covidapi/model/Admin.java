package org.polytech.covidapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Admin {
    @Id
    @Setter
    private Long id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String hash;

    @OneToOne(cascade = CascadeType.ALL)
    @Getter
    @Setter
    private Centre centre;

    public Admin(Centre centre) {
        setCentre(centre);
    }

    protected Admin() {
    }
}
