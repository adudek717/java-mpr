package pl.pjatk.gameplay.service;

import pl.pjatk.gameplay.dto.MovieDto;
import pl.pjatk.gameplay.model.Movie;

import java.util.List;

public interface MovieService {
    Movie getMovieById(Long id);

    List<Movie> getMoviesByCategory(String category);

    List<Movie> getAllMovies();

    Movie addMovie(MovieDto movieDto);

    void deleteMovie(Long id);

    Movie changeDescription(Long id, String description);

    Movie getRandomMovie();
}
