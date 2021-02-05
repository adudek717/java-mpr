package pl.pjatk.gameplay.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pjatk.gameplay.model.Movie;

public interface MovieRepository extends CrudRepository<Movie,Long> {
}
