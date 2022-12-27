package dev.evilasio.lukedirector.service.swapiIntegration;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.evilasio.lukedirector.client.SwapiWebClient;
import dev.evilasio.lukedirector.domain.filter.FilmFilter;
import dev.evilasio.lukedirector.domain.response.swapi.SwapiFilmResponse;
import dev.evilasio.lukedirector.domain.response.swapi.SwapiPeopleResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class SwapiIntegrationServiceImpl implements SwapiIntegrationService {

    private final SwapiWebClient swapiWebClient;

    @Override
    public List<SwapiFilmResponse> getAllLukeFilms() {
        Flux<String> filmsFlux = swapiWebClient.getLuke()
                .map(SwapiPeopleResponse::getFilms)
                .flatMapMany(Flux::fromIterable);

        return filmsFlux.flatMap(swapiWebClient::getFilm)
                .collectList().block();
    }

    @Override
    public List<SwapiFilmResponse> getLukeFilmsWithFilter(FilmFilter filter) {
        return getAllLukeFilms().stream()
                .filter(film -> {
                    if (filter == null) {
                        return true;
                    }
                    if (filter.getEpisodeId() == null && (filter.getTitle() == null || filter.getTitle().isEmpty())) {
                        return true;
                    }
                    if (filter.getTitle() != null && !filter.getTitle().isBlank()
                            && !film.getTitle().toLowerCase().contains(filter.getTitle().toLowerCase())) {
                        return false;
                    }
                    if (filter.getEpisodeId() != null && !film.getEpisodeId().equals(filter.getEpisodeId())) {
                        return false;
                    }
                    return true;
                })
                .toList();
    }

}
