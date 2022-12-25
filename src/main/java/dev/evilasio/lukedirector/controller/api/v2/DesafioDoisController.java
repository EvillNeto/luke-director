package dev.evilasio.lukedirector.controller.api.v2;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.evilasio.lukedirector.domain.dto.FilmDto;
import dev.evilasio.lukedirector.domain.filter.FilmFilter;
import dev.evilasio.lukedirector.domain.form.FilmForm;
import dev.evilasio.lukedirector.service.film.FilmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/luke-movies")
@RequiredArgsConstructor
public class DesafioDoisController {

    private final FilmService service;

    @GetMapping()
    public ResponseEntity<Page<FilmDto>> getAllMovies(@ParameterObject FilmFilter filter,
            @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(service.getFilms(filter, pageable));
    }

    @PostMapping()
    public ResponseEntity<List<FilmDto>> saveLukeMovies(@RequestBody @Valid FilmForm form) {
        return ResponseEntity.ok(service.saveFilm(form));
    }

    @GetMapping("/seed-movies")
    public ResponseEntity<Void> seedLukeMovies() {
        service.seedLukeFilms();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/clear-all-movies")
    public ResponseEntity<Void> clearAllMovies() {
        service.clearAllFilms();
        return ResponseEntity.ok().build();
    }

}
