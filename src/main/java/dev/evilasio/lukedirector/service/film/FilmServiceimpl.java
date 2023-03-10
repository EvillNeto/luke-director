package dev.evilasio.lukedirector.service.film;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.evilasio.lukedirector.domain.dto.FilmDto;
import dev.evilasio.lukedirector.domain.entity.FilmEntiity;
import dev.evilasio.lukedirector.domain.filter.FilmFilter;
import dev.evilasio.lukedirector.domain.form.FilmForm;
import dev.evilasio.lukedirector.exception.StandardException;
import dev.evilasio.lukedirector.repository.FilmRepository;
import dev.evilasio.lukedirector.repository.specification.FilmSpecification;
import dev.evilasio.lukedirector.service.swapiIntegration.SwapiIntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmServiceimpl implements FilmService {

    private final SwapiIntegrationService swapiIntegrationService;

    private final FilmRepository filmRepository;

    @Override
    public Page<FilmDto> getFilms(FilmFilter filter, Pageable pageable) {
        Page<FilmEntiity> films = filmRepository.findAll(FilmSpecification.filmFilter(filter), pageable);
        return FilmDto.toDto(films);
    }

    @Override
    public List<FilmDto> saveFilm(FilmForm form) {
        Optional<FilmEntiity> filmOpt = filmRepository.findByEpisodeId(form.getEpisodeId());
        if (filmOpt.isPresent()) {
            throw new StandardException("Film Already Existes", "There is a film with the id provided",
                    HttpStatus.BAD_REQUEST, filmOpt.get());
        } else {
            FilmEntiity film = form.toEntity();
            filmRepository.save(film);
            List<FilmEntiity> films = filmRepository.findAll();
            log.info("New Film list:{}", films);
            return FilmDto.toDto(films);
        }
    }

    @Override
    public void seedLukeFilms() {
        if (filmRepository.count() == 0l) {
            List<FilmEntiity> films = FilmEntiity.fromSwapi(swapiIntegrationService.getAllLukeFilms());
            filmRepository.saveAll(films);
        } else {
            throw new StandardException("Seed not allowed", "Seed can only be done on a clean database",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void clearAllFilms() {
        filmRepository.deleteAll();
    }

}
