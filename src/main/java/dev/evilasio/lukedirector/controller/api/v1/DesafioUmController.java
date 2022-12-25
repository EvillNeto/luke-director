package dev.evilasio.lukedirector.controller.api.v1;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.evilasio.lukedirector.domain.dto.FilmDto;
import dev.evilasio.lukedirector.domain.filter.FilmFilter;
import dev.evilasio.lukedirector.service.swapiIntegration.SwapiIntegrationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/luke-movies")
@RequiredArgsConstructor
public class DesafioUmController {

    private final SwapiIntegrationService swapiIntegrationService;

    @GetMapping()
    public ResponseEntity<List<FilmDto>> getLukeMovies(@ParameterObject FilmFilter filter) {
        return ResponseEntity.ok(swapiIntegrationService.getLukeFilmsWithFilter(filter));
    }
}
