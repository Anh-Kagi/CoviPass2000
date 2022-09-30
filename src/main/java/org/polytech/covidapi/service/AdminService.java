package org.polytech.covidapi.service;

import org.polytech.covidapi.model.Admin;
import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.repository.AdminRepository;
import org.polytech.covidapi.repository.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository admins;
    private final CentreRepository centres;

    @Autowired
    public AdminService(AdminRepository admins, CentreRepository centres) {
        this.admins = admins;
        this.centres = centres;
    }

    public Admin create(Long centreId) {
        Centre centre = centres.findCentreById(centreId);
        Admin admin = new Admin(centre);
        return admins.save(admin);
    }

    public Admin get(Long id) {
        return admins.findAdminById(id);
    }

    public Admin update(Long id, Long centreId) {
        Admin admin = get(id);
        if (admin != null) {
            Centre centre = centres.findCentreById(centreId);
            admin.setCentre(centre);
            return admins.save(admin);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (admins.existsById(id)) {
            admins.deleteById(id);
            return true;
        }
        return false;
    }
}
