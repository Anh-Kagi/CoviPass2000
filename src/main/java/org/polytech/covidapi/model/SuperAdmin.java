package org.polytech.covidapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SuperAdmin {

    private Long idSuperAdmin;

    public void setIdSuperAdmin(Long idSuperAdmin) {
        this.idSuperAdmin = idSuperAdmin;
    }

    @Id
    public Long getIdSuperAdmin() {
        return idSuperAdmin;
    }
}
