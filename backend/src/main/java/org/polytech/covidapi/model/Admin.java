package org.polytech.covidapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Admin extends BaseUser {
    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private Centre centre;

    public Admin(Centre centre) {
        setCentre(centre);
    }

    protected Admin() {
    }

    @Override
    public String getRole() {
        return "ROLE_ADMIN";
    }
}
