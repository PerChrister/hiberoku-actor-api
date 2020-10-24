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
public class Actor {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique = true)
    public Integer id;

    @Column(nullable=false)
    public String first_name;

    @Column(nullable=false)
    public String last_name;

    @Column(nullable=false)
    public String dob;

    @Column(nullable=false)
    public String imdb_url;

    @JsonGetter("movies")
    public List<String> actors() {
        return movies.stream()
                .map(movie -> {
                    return "/movie/" + movie.id;
                }).collect(Collectors.toList());
    }

    @ManyToMany(mappedBy = "actors",fetch=FetchType.LAZY)
    public Set<Movie> movies = new HashSet<Movie>();

}

