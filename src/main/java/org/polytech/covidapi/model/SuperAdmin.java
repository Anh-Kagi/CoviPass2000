package org.polytech.covidapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SuperAdmin {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }
}
