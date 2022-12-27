package dev.evilasio.lukedirector.domain.response.swapi;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SwapiFilmResponse {

    private String title;

    @JsonProperty("episode_id")
    private Long episodeId;

    private String director;

    @JsonProperty("release_date")
    private LocalDate releaseDate;
}
