package org.polytech.covidapi.service;

import lombok.NonNull;
import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.repository.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CentreService {
	private final CentreRepository centres;

	@Autowired
	public CentreService(CentreRepository centres) {
		this.centres = centres;
	}

	public Centre create(@NonNull String nom, @NonNull String ville, @NonNull String adresse) {
		return centres.save(new Centre(nom, ville, adresse));
	}

	public Optional<Centre> get(@NonNull Long id) {
		return centres.findById(id);
	}

	public List<Centre> getAllByVille(@NonNull String ville) {
		return centres.findAllByVilleIgnoreCase(ville);
	}

	public Centre update(@NonNull Centre centre, String nom, String ville, String adresse) {
		if (nom != null) {
			centre.setNom(nom);
		}
		if (ville != null) {
			centre.setVille(ville);
		}
		if (adresse != null) {
			centre.setAdresse(adresse);
		}
		return centres.save(centre);
	}

	public void delete(@NonNull Centre centre) {
		centres.delete(centre);
	}
}
