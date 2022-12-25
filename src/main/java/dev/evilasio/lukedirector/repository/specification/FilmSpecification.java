package dev.evilasio.lukedirector.repository.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;

import dev.evilasio.lukedirector.domain.entity.FilmEntiity;
import dev.evilasio.lukedirector.domain.filter.FilmFilter;
import dev.evilasio.lukedirector.exception.StandardException;
import jakarta.persistence.criteria.Predicate;

public class FilmSpecification {

    private FilmSpecification() {
        throw new StandardException("Utility Class", "Class 'FilmSpecification' is a utility class",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static Specification<FilmEntiity> filmFilter(FilmFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter != null) {
                if (filter.getEpisodeId() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("episodeId"), filter.getEpisodeId()));
                }
                if (filter.getTitle() != null && !filter.getTitle().isBlank()) {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),
                            "%" + filter.getTitle().toLowerCase() + "%"));
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
