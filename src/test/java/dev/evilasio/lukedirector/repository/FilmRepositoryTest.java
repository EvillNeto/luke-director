package dev.evilasio.lukedirector.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

import dev.evilasio.lukedirector.domain.entity.FilmEntiity;

@DataJpaTest
public class FilmRepositoryTest {

    @Autowired
    private FilmRepository underTest;

    @Test
    void shouldFindFilmByEpisodeId() {
        // given
        Long epsId = 5L;
        FilmEntiity film = FilmEntiity.builder()
                .director("Tester")
                .title("Test 1")
                .episodeId(epsId)
                .releaseDate(LocalDate.now())
                .build();
        underTest.save(film);
        // when
        Optional<FilmEntiity> filmOpt = underTest.findByEpisodeId(epsId);
        // then
        assertThat(filmOpt).isPresent();
    }
    @Test
    void shouldNotFindFilmByEpisodeId() {
        // given
        Long epsId = 5L;
        FilmEntiity film = FilmEntiity.builder()
                .director("Tester")
                .title("Test 1")
                .episodeId(7L)
                .releaseDate(LocalDate.now())
                .build();
        underTest.save(film);
        // when
        Optional<FilmEntiity> filmOpt = underTest.findByEpisodeId(epsId);
        // then
        assertThat(filmOpt).isEmpty();
    }
}
