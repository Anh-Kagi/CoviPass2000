package org.polytech.covidapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SuperAdmin {

    private Long id;

    @Id
    public Long getId() {
        return id;
    }
}
