package dev.evilasio.lukedirector.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dev.evilasio.lukedirector.domain.entity.FilmEntiity;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntiity, Long>, JpaSpecificationExecutor<FilmEntiity> {

    Optional<FilmEntiity> findByEpisodeId(Long id);
}
