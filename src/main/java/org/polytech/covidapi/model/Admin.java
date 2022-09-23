package org.polytech.covidapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Admin {
    private Long idAdmin;
    @OneToOne
    private Centre centre;

    public void setIdAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
    }

    @Id
    public Long getIdAdmin() {
        return idAdmin;
    }
}
