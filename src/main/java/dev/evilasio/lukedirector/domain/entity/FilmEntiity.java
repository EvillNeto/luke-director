package dev.evilasio.lukedirector.domain.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmEntiity extends BaseEntity{

    private String title;

    private Long episodeId;

    private String director;

    private LocalDate releaseDate;
}
