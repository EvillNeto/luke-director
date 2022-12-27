package dev.evilasio.lukedirector.service.film;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import dev.evilasio.lukedirector.domain.entity.FilmEntiity;
import dev.evilasio.lukedirector.domain.filter.FilmFilter;
import dev.evilasio.lukedirector.domain.form.FilmForm;
import dev.evilasio.lukedirector.repository.FilmRepository;
import dev.evilasio.lukedirector.service.swapiIntegration.SwapiIntegrationService;

@ExtendWith(MockitoExtension.class)
public class FilmServiceimplTest {

    @Mock
    private SwapiIntegrationService swapiIntegrationService;

    @Mock
    private FilmRepository repository;

    private FilmServiceimpl underTest;

    @BeforeEach
    void setup() {
        underTest = new FilmServiceimpl(swapiIntegrationService, repository);
    }

    private Page<FilmEntiity> getPage() {
        List<FilmEntiity> list = new ArrayList<>();
        FilmEntiity film1 = FilmEntiity.builder().build();
        list.add(film1);
        return new PageImpl<>(list);
    }

    @Test
    void testGetFilms() {
        // given
        FilmFilter filmFilter = FilmFilter.builder().build();
        Pageable pageable = Pageable.ofSize(1);
        // when
        Mockito.doReturn(getPage()).when(repository).findAll(any(Specification.class), any(Pageable.class));
        underTest.getFilms(filmFilter, pageable);
        // then
        verify(repository).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    void shouldCallSaveFilm() {
        // given
        FilmForm form = FilmForm.builder().episodeId(1L).build();
        // when
        Mockito.when(repository.findByEpisodeId(anyLong())).thenReturn(Optional.empty());
        Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());
        underTest.saveFilm(form);
        // then
        verify(repository).save(any(FilmEntiity.class));
        verify(repository).findAll();
    }

    @Test
    void shouldThrowBeforeSaveFilm() {
        // given
        FilmForm form = FilmForm.builder().episodeId(1L).build();
        // when
        Mockito.when(repository.findByEpisodeId(anyLong())).thenReturn(Optional.of(FilmEntiity.builder().build()));
        // then
        assertThatThrownBy(() -> underTest.saveFilm(form)).hasMessageContaining("There is a film with the id provided");
    }

    @Test
    void shouldCallSaveAll() {
        // when
        Mockito.when(repository.count()).thenReturn(0L);
        Mockito.when(swapiIntegrationService.getAllLukeFilms()).thenReturn(new ArrayList<>());
        underTest.seedLukeFilms();
        // then
        verify(repository).saveAll(anyList());
    }

    @Test
    void shouldThrowExceptionDuringSeed() {
        // when
        Mockito.when(repository.count()).thenReturn(1L);
        // then
        assertThatThrownBy(() -> underTest.seedLukeFilms())
                .hasMessageContaining("Seed can only be done on a clean database");
    }

    @Test
    void testClearAll() {
        // when
        underTest.clearAllFilms();
        // then
        verify(repository).deleteAll();
    }
}
