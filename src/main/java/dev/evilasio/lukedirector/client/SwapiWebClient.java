package dev.evilasio.lukedirector.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import dev.evilasio.lukedirector.domain.response.swapi.SwapiFilmResponse;
import dev.evilasio.lukedirector.domain.response.swapi.SwapiPeopleResponse;
import reactor.core.publisher.Mono;

@Service
public class SwapiWebClient {

    private static final String SWAPI_LUKE_URL = "https://swapi.dev/api/people/1/";

    private WebClient client(String url) {
        return WebClient.create(url);
    }

    private Mono<SwapiPeopleResponse> getLuke() {
        return client(SWAPI_LUKE_URL)
                .get()
                .uri(uri -> uri.path("")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(SwapiPeopleResponse.class);
                    } else {
                        return Mono.empty();
                    }
                });
    }

    private Mono<SwapiFilmResponse> getFilm(String url) {
        return client(url)
                .get()
                .uri(uri -> uri.path("")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(SwapiFilmResponse.class);
                    } else {
                        return Mono.empty();
                    }
                });
    }
}
