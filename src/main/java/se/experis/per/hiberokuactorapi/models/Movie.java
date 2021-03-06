package se.experis.per.hiberokuactorapi.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

public class Movie {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique = true)
    public Integer id;

    @Column(nullable=false)
    public String title;

    @Column(nullable=false)
    public String genre;

    @Column(nullable=false)
    public String run_time;

    @Column(nullable=false)
    public String premier_date;

    @JsonGetter("actors")
    public List<String> actors() {
        return actors.stream()
                .map(actor -> {
                    return "/actor/" + actor.id;
                }).collect(Collectors.toList());
    }

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name = "Movie_Actor",
            joinColumns = { @JoinColumn(name = "movie_id") },
            inverseJoinColumns = { @JoinColumn(name = "actor_id") }
    )
    public Set<Actor> actors = new HashSet<Actor>();

}
