package dev.evilasio.lukedirector.service.swapiIntegration;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.evilasio.lukedirector.domain.filter.FilmFilter;
import dev.evilasio.lukedirector.domain.response.swapi.SwapiFilmResponse;

@Service
public interface SwapiIntegrationService {

    List<SwapiFilmResponse> getAllLukeFilms();

    List<SwapiFilmResponse> getLukeFilmsWithFilter(FilmFilter filter);
}
