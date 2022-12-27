package dev.evilasio.lukedirector.service.swapiIntegration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.evilasio.lukedirector.client.SwapiWebClient;
import dev.evilasio.lukedirector.domain.filter.FilmFilter;
import dev.evilasio.lukedirector.domain.response.swapi.SwapiFilmResponse;
import dev.evilasio.lukedirector.domain.response.swapi.SwapiPeopleResponse;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class SwapiIntegrationServiceImplTest {

    @Mock
    private SwapiWebClient swapiWebClient;

    private SwapiIntegrationServiceImpl underTest;

    @BeforeEach
    void setup() {
        underTest = new SwapiIntegrationServiceImpl(swapiWebClient);
    }

    private Mono<SwapiPeopleResponse> lukeMono() {
        return Mono.just(SwapiPeopleResponse.builder()
                .films(List.of("ulr 1", "ulr 2", "url 3"))
                .build());
    }

    private Mono<SwapiFilmResponse> filmMono(String url) {
        return Mono.just(SwapiFilmResponse.builder()
                .director(url)
                .title(url)
                .build());
    }

    private List<SwapiFilmResponse> filmList() {
        List<SwapiFilmResponse> films = new ArrayList<>();

        SwapiFilmResponse film1 = SwapiFilmResponse.builder()
                .title("test 1")
                .episodeId(1L)
                .build();
        films.add(film1);

        SwapiFilmResponse film2 = SwapiFilmResponse.builder()
                .title("title 2")
                .episodeId(2L)
                .build();
        films.add(film2);

        SwapiFilmResponse film3 = SwapiFilmResponse.builder()
                .title("test 3")
                .episodeId(3L)
                .build();
        films.add(film3);

        return films;
    }

    @Test
    void canGetLukeFilms() {
        // when
        Mockito.when(swapiWebClient.getLuke()).thenReturn(lukeMono());
        Mockito.when(swapiWebClient.getFilm(anyString())).thenReturn(filmMono("url 1"));
        Mockito.when(swapiWebClient.getFilm(anyString())).thenReturn(filmMono("url 2"));
        Mockito.when(swapiWebClient.getFilm(anyString())).thenReturn(filmMono("url 3"));
        underTest.getAllLukeFilms();
        // then
        verify(swapiWebClient).getLuke();
        verify(swapiWebClient, times(3)).getFilm(anyString());
    }

    @Test
    void testGetLukeTwoFilmsWithTitleFilter() {
        // given
        SwapiIntegrationServiceImpl spy = Mockito.spy(underTest);
        FilmFilter filter = FilmFilter.builder().title("test").build();
        // when
        Mockito.doReturn(filmList()).when(spy).getAllLukeFilms();
        List<SwapiFilmResponse> lukeFilmsWithFilter = spy.getLukeFilmsWithFilter(filter);
        // then
        assertThat(lukeFilmsWithFilter).hasSize(2);
    }

    @Test
    void testGetLukeOneFilmsWithTitleFilter() {
        // given
        SwapiIntegrationServiceImpl spy = Mockito.spy(underTest);
        FilmFilter filter = FilmFilter.builder().title("title").build();
        // when
        Mockito.doReturn(filmList()).when(spy).getAllLukeFilms();
        List<SwapiFilmResponse> lukeFilmsWithFilter = spy.getLukeFilmsWithFilter(filter);
        // then
        assertThat(lukeFilmsWithFilter).hasSize(1);
    }

    @Test
    void testGetLukeNoneFilmsWithTitleFilter() {
        // given
        SwapiIntegrationServiceImpl spy = Mockito.spy(underTest);
        FilmFilter filter = FilmFilter.builder().title("test 2").build();
        // when
        Mockito.doReturn(filmList()).when(spy).getAllLukeFilms();
        List<SwapiFilmResponse> lukeFilmsWithFilter = spy.getLukeFilmsWithFilter(filter);
        // then
        assertThat(lukeFilmsWithFilter).isEmpty();
    }

    @Test
    void testGetLukeOneFilmsWithEpsIdFilter() {
        // given
        SwapiIntegrationServiceImpl spy = Mockito.spy(underTest);
        FilmFilter filter = FilmFilter.builder().episodeId(1L).build();
        // when
        Mockito.doReturn(filmList()).when(spy).getAllLukeFilms();
        List<SwapiFilmResponse> lukeFilmsWithFilter = spy.getLukeFilmsWithFilter(filter);
        // then
        assertThat(lukeFilmsWithFilter).hasSize(1);
    }

    @Test
    void testGetLukeNoneFilmsWithEpsIdFilter() {
        // given
        SwapiIntegrationServiceImpl spy = Mockito.spy(underTest);
        FilmFilter filter = FilmFilter.builder().episodeId(4L).build();
        // when
        Mockito.doReturn(filmList()).when(spy).getAllLukeFilms();
        List<SwapiFilmResponse> lukeFilmsWithFilter = spy.getLukeFilmsWithFilter(filter);
        // then
        assertThat(lukeFilmsWithFilter).isEmpty();
    }

    @Test
    void testGetLukeOneFilmsWithFullFilter() {
        // given
        SwapiIntegrationServiceImpl spy = Mockito.spy(underTest);
        FilmFilter filter = FilmFilter.builder().title("test").episodeId(3L).build();
        // when
        Mockito.doReturn(filmList()).when(spy).getAllLukeFilms();
        List<SwapiFilmResponse> lukeFilmsWithFilter = spy.getLukeFilmsWithFilter(filter);
        // then
        assertThat(lukeFilmsWithFilter).hasSize(1);
    }

    @Test
    void testGetLukeNoneFilmsWithFullFilter() {
        // given
        SwapiIntegrationServiceImpl spy = Mockito.spy(underTest);
        FilmFilter filter = FilmFilter.builder().title("title").episodeId(3L).build();
        // when
        Mockito.doReturn(filmList()).when(spy).getAllLukeFilms();
        List<SwapiFilmResponse> lukeFilmsWithFilter = spy.getLukeFilmsWithFilter(filter);
        // then
        assertThat(lukeFilmsWithFilter).isEmpty();
    }
}
