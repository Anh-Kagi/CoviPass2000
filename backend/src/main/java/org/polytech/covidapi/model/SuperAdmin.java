package org.polytech.covidapi.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SuperAdmin {
    @Id
    @Getter
    private Long id;
}
