package dev.evilasio.lukedirector.service.swapiIntegration;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.evilasio.lukedirector.client.SwapiWebClient;
import dev.evilasio.lukedirector.domain.dto.FilmDto;
import dev.evilasio.lukedirector.domain.filter.FilmFilter;
import dev.evilasio.lukedirector.domain.response.swapi.SwapiFilmResponse;
import dev.evilasio.lukedirector.domain.response.swapi.SwapiPeopleResponse;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class SwapiIntegrationServiceImpl implements SwapiIntegrationService {

    private final SwapiWebClient swapiWebClient;

    @Override
    public List<FilmDto> getAllLukeFilms() {
        Flux<String> filmsFlux = swapiWebClient.getLuke()
                .map(SwapiPeopleResponse::getFilms)
                .flatMapMany(Flux::fromIterable);

        List<SwapiFilmResponse> films = filmsFlux.flatMap(swapiWebClient::getFilm)
                .collectList().block();

        return FilmDto.toDto(films);
    }

    @Override
    public List<FilmDto> getLukeFilmsWithFilter(FilmFilter filter) {
        return getAllLukeFilms().stream()
                .filter(film -> {
                    if (filter == null) {
                        return true;
                    }
                    if (filter.getTitle() != null && !filter.getTitle().isEmpty()) {
                        if (!film.getTitle().toLowerCase().contains(filter.getTitle().toLowerCase())) {
                            return false;
                        }
                    }
                    if (filter.getEpisodeId() != null) {
                        if (!film.getEpisodeId().equals(filter.getEpisodeId())) {
                            return false;
                        }
                    }
                    return true;
                })
                .toList();
    }

}
