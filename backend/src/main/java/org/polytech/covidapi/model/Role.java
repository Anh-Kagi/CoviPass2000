package org.polytech.covidapi.model;

import lombok.Getter;

public enum Role {
    SUPER_ADMIN("SUPER_ADMIN"),
    ADMIN("ADMIN"),
    MEDECIN("MEDECIN");

    @Getter
    private final String name;

    Role(String name) {
        this.name = name;
    }
}
