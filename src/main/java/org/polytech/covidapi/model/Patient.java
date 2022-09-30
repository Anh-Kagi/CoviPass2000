package org.polytech.covidapi.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Patient {
    @Id
    private Long id;

    private String nom;
    private String prenom;
    private String mail;
    private long telephone;
    private boolean vaccine;

    public boolean getVaccine() {
        return vaccine;
    }

    public void setVaccine(boolean vaccine) {
        this.vaccine = vaccine;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public long getTelephone() {
        return telephone;
    }

    public void setTelephone(long telephone) {
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }
}
