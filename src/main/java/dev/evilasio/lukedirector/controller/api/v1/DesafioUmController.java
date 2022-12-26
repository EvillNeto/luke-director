package dev.evilasio.lukedirector.controller.api.v1;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.evilasio.lukedirector.domain.dto.FilmDto;
import dev.evilasio.lukedirector.domain.filter.FilmFilter;
import dev.evilasio.lukedirector.service.swapiIntegration.SwapiIntegrationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/luke-movies")
@RequiredArgsConstructor
public class DesafioUmController {

    private final SwapiIntegrationService swapiIntegrationService;

    @GetMapping()
    @Operation(description = "Metodo busca os filmes do personagem Luke Skywalker em tempo real e faz montagem paralela dos objetos")
    public ResponseEntity<Page<FilmDto>> getLukeMovies(@ParameterObject FilmFilter filter,
            @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(FilmDto.toPage(swapiIntegrationService.getLukeFilmsWithFilter(filter), pageable));
    }
}
