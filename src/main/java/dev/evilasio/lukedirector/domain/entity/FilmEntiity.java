package dev.evilasio.lukedirector.domain.entity;

import java.time.LocalDate;
import java.util.List;

import dev.evilasio.lukedirector.domain.response.swapi.SwapiFilmResponse;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FilmEntiity extends BaseEntity {

    private String title;

    private Long episodeId;

    private String director;

    private LocalDate releaseDate;

    public FilmEntiity(SwapiFilmResponse film) {
        this.director = film.getDirector();
        this.title = film.getTitle();
        this.episodeId = film.getEpisodeId();
        this.releaseDate = film.getReleaseDate();
    }

    public static FilmEntiity fromSwapi(SwapiFilmResponse film) {
        return film == null ? null : new FilmEntiity(film);
    }

    public static List<FilmEntiity> fromSwapi(List<SwapiFilmResponse> films) {
        return films.stream().map(FilmEntiity::fromSwapi).toList();
    }
}
