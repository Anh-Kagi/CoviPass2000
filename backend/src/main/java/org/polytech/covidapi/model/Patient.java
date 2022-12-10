package org.polytech.covidapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Patient {
    @Id
    @Getter
    private Long id;

    @Getter
    @Setter
    private String nom;

    @Getter
    @Setter
    private String prenom;

    @Getter
    @Setter
    private String mail;

    @Getter
    @Setter
    private long telephone;

    public Patient(String nom, String prenom, String mail, long telephone) {
        setNom(nom);
        setPrenom(prenom);
        setMail(mail);
        setTelephone(telephone);
    }

    protected Patient() {
    }
}
