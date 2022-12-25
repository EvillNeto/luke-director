package dev.evilasio.lukedirector.service.film;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.evilasio.lukedirector.domain.dto.FilmDto;
import dev.evilasio.lukedirector.domain.filter.FilmFilter;
import dev.evilasio.lukedirector.domain.form.FilmForm;
import dev.evilasio.lukedirector.repository.FilmRepository;
import dev.evilasio.lukedirector.service.swapiIntegration.SwapiIntegrationService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilmServiceimpl implements FilmService {

    private final SwapiIntegrationService swapiIntegrationService;

    private final FilmRepository filmRepository;


    @Override
    public List<FilmDto> getFilms(FilmFilter filter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<FilmDto> saveFilm(FilmForm form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void seedLukeFilms() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void clearAllFilms() {
        // TODO Auto-generated method stub
        
    }

}
