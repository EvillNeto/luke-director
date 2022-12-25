package dev.evilasio.lukedirector.service.swapiIntegration;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.evilasio.lukedirector.domain.dto.FilmDto;
import dev.evilasio.lukedirector.domain.filter.FilmFilter;

@Service
public interface SwapiIntegrationService {
    
    List<FilmDto> getAllLukeFilms();

    List<FilmDto> getLukeFilmsWithFilter(FilmFilter filter);
}
