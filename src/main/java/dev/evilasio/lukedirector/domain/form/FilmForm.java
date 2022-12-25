package dev.evilasio.lukedirector.domain.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import dev.evilasio.lukedirector.domain.entity.FilmEntiity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class FilmForm {

    @NotBlank(message = "field 'title' can't be blank or null")
    private String title;

    @JsonProperty("episode_id")
    @NotNull(message = "field 'episode_id' can't be blank or null")
    private Long episodeId;

    @NotBlank(message = "field 'director' can't be blank or null")
    private String director;

    @JsonProperty("release_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "field 'release_date' can't be blank or null")
    private LocalDate releaseDate;

    public FilmEntiity toEntity(){
        return FilmEntiity.builder()
        .title(this.title)
        .episodeId(this.episodeId)
        .director(this.director)
        .releaseDate(this.releaseDate)
        .build();
    }
}
