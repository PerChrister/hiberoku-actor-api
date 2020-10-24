package se.experis.per.hiberokuactorapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.experis.per.hiberokuactorapi.models.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
