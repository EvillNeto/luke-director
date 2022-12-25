package dev.evilasio.lukedirector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import dev.evilasio.lukedirector.domain.entity.FilmEntiity;

public interface FilmRepository extends JpaRepository<FilmEntiity, Long>, JpaSpecificationExecutor<FilmEntiity> {

}
