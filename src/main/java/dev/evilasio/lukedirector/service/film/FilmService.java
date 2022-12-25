package dev.evilasio.lukedirector.service.film;

import java.util.List;

import dev.evilasio.lukedirector.domain.dto.FilmDto;
import dev.evilasio.lukedirector.domain.filter.FilmFilter;
import dev.evilasio.lukedirector.domain.form.FilmForm;

public interface FilmService {
    
    List<FilmDto> getFilms(FilmFilter filter);

    List<FilmDto> saveFilm(FilmForm form);

    void seedLukeFilms();

    void clearAllFilms();
}
