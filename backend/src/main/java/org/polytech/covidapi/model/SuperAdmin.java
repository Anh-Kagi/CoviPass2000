package org.polytech.covidapi.model;

import javax.persistence.Entity;

@Entity
public class SuperAdmin extends User {
    @Override
    public String getRole() {
        return "ROLE_SUPER_ADMIN";
    }
}
