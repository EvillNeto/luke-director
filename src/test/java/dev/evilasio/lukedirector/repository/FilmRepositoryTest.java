package dev.evilasio.lukedirector.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import dev.evilasio.lukedirector.domain.entity.FilmEntiity;
import dev.evilasio.lukedirector.domain.filter.FilmFilter;
import dev.evilasio.lukedirector.repository.specification.FilmSpecification;

@DataJpaTest
public class FilmRepositoryTest {

    @Autowired
    private FilmRepository underTest;

    @BeforeEach
    void setup() {
        // given
        List<FilmEntiity> films = new ArrayList<>();

        FilmEntiity film = FilmEntiity.builder()
                .director("Tester")
                .title("Test 1")
                .episodeId(1L)
                .releaseDate(LocalDate.now())
                .build();
        films.add(film);
        FilmEntiity film2 = FilmEntiity.builder()
                .director("Tester")
                .title("Title 2")
                .episodeId(2L)
                .releaseDate(LocalDate.now())
                .build();
        films.add(film2);
        FilmEntiity film3 = FilmEntiity.builder()
                .director("Tester")
                .title("Test 3")
                .episodeId(3L)
                .releaseDate(LocalDate.now())
                .build();
        films.add(film3);
        underTest.saveAll(films);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindFilmByEpisodeId() {
        // given
        Long epsId = 1L;
        // when
        Optional<FilmEntiity> filmOpt = underTest.findByEpisodeId(epsId);
        // then
        assertThat(filmOpt).isPresent();
    }

    @Test
    void shouldNotFindFilmByEpisodeId() {
        // given
        Long epsId = 5L;
        // when
        Optional<FilmEntiity> filmOpt = underTest.findByEpisodeId(epsId);
        // then
        assertThat(filmOpt).isEmpty();
    }

    @Test
    void shouldFindOneByTitle(){
        // given
        FilmFilter filter = FilmFilter.builder().title("title").build();
        // when
        List<FilmEntiity> films = underTest.findAll(FilmSpecification.filmFilter(filter));
        // then
        assertThat(films).hasSize(1);
    }

    @Test
    void shouldFindTwoByTitle(){
        // given
        FilmFilter filter = FilmFilter.builder().title("test").build();
        // when
        List<FilmEntiity> films = underTest.findAll(FilmSpecification.filmFilter(filter));
        // then
        assertThat(films).hasSize(2);
    }

    @Test
    void shouldFindOneByEpsId(){
        // given
        FilmFilter filter = FilmFilter.builder().episodeId(1L).build();
        // when
        List<FilmEntiity> films = underTest.findAll(FilmSpecification.filmFilter(filter));
        // then
        assertThat(films).hasSize(1);
    }

    @Test
    void shouldFindNoneByEpsId(){
        // given
        FilmFilter filter = FilmFilter.builder().episodeId(4L).build();
        // when
        List<FilmEntiity> films = underTest.findAll(FilmSpecification.filmFilter(filter));
        // then
        assertThat(films).isEmpty();;
    }

    @Test
    void shouldFindOneByTitleAndEpsId(){
        // given
        FilmFilter filter = FilmFilter.builder().title("test").episodeId(1L).build();
        // when
        List<FilmEntiity> films = underTest.findAll(FilmSpecification.filmFilter(filter));
        // then
        assertThat(films).hasSize(1);
    }

    @Test
    void shouldFindNoneByTitleAndEpsId(){
        // given
        FilmFilter filter = FilmFilter.builder().title("test").episodeId(2L).build();
        // when
        List<FilmEntiity> films = underTest.findAll(FilmSpecification.filmFilter(filter));
        // then
        assertThat(films).isEmpty();
    }
}
