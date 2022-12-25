package dev.evilasio.lukedirector.service.film;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.evilasio.lukedirector.domain.dto.FilmDto;
import dev.evilasio.lukedirector.domain.filter.FilmFilter;
import dev.evilasio.lukedirector.domain.form.FilmForm;

public interface FilmService {

    Page<FilmDto> getFilms(FilmFilter filter, Pageable pageable);

    List<FilmDto> saveFilm(FilmForm form);

    void seedLukeFilms();

    void clearAllFilms();
}
