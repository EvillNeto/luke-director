package dev.evilasio.lukedirector.domain.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonProperty;

import dev.evilasio.lukedirector.domain.entity.FilmEntiity;
import dev.evilasio.lukedirector.domain.response.swapi.SwapiFilmResponse;
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
public class FilmDto {

    private String title;

    @JsonProperty("episode_id")
    private Long episodeId;

    private String director;

    @JsonProperty("release_date")
    private String releaseDate;

    public FilmDto(SwapiFilmResponse filmResponse) {
        this.title = filmResponse.getTitle();
        this.episodeId = filmResponse.getEpisodeId();
        this.director = filmResponse.getDirector();
        this.releaseDate = filmResponse.getReleaseDate() == null ? null
                : filmResponse.getReleaseDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public FilmDto(FilmEntiity film) {
        this.title = film.getTitle();
        this.episodeId = film.getEpisodeId();
        this.director = film.getDirector();
        this.releaseDate = film.getReleaseDate() == null ? null
                : film.getReleaseDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static FilmDto toDto(SwapiFilmResponse film) {
        return film == null ? null : new FilmDto(film);
    }

    public static List<FilmDto> toDtoFromSwapi(List<SwapiFilmResponse> films) {
        return films.stream().map(FilmDto::toDto).toList();
    }

    public static FilmDto toDto(FilmEntiity film) {
        return film == null ? null : new FilmDto(film);
    }

    public static List<FilmDto> toDto(List<FilmEntiity> films) {
        return films.stream().map(FilmDto::toDto).toList();
    }

    public static Page<FilmDto> toDto(Page<FilmEntiity> films) {
        return films.map(FilmDto::toDto);
    }

    public static Page<FilmDto> toPage(List<SwapiFilmResponse> films, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), films.size());
        Page<SwapiFilmResponse> swapiFilmPage = new PageImpl<>(films.subList(start, end), pageable, films.size());
        return swapiFilmPage.map(FilmDto::toDto);
    }
}
