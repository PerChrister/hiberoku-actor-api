package se.experis.per.hiberokuactorapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.experis.per.hiberokuactorapi.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}

