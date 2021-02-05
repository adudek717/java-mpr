package pl.pjatk.gameplay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.dto.MovieDto;
import pl.pjatk.gameplay.exception.CategoryNotFoundException;
import pl.pjatk.gameplay.exception.MovieNotFoundException;
import pl.pjatk.gameplay.model.Category;
import pl.pjatk.gameplay.model.Movie;
import pl.pjatk.gameplay.repository.CategoryRepository;
import pl.pjatk.gameplay.repository.MovieRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, CategoryRepository categoryRepository) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(
                () -> new MovieNotFoundException(id));
    }

    @Override
    public List<Movie> getMoviesByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName).orElseThrow(
                () -> new CategoryNotFoundException(categoryName));
        return category.getMovies();
    }

    @Override
    public List<Movie> getAllMovies() {
        return (List<Movie>) movieRepository.findAll();
    }

    @Override
    public Movie addMovie(MovieDto movieDto) {
        Movie movie = new Movie(movieDto.getTitle(),movieDto.getDescription(),
                getCategory(movieDto.getCategoryName()));
        return movieRepository.save(movie);
    }

    private Category getCategory(String name){
        Optional<Category> category = categoryRepository.findByName(name);
        if (category.isPresent())
            return category.get();
        return categoryRepository.save(new Category(name));

    }
    @Override
    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(
                () -> new MovieNotFoundException(id));
        movieRepository.delete(movie);
    }

    @Override
    public Movie changeDescription(Long id, String description) {
       Movie movie = movieRepository.findById(id).orElseThrow(
                () -> new MovieNotFoundException(id));
       movie.setDescription(description);
       return movieRepository.save(movie);
    }

    @Override
    public Movie getRandomMovie() {
        List<Movie> movies = (List<Movie>) movieRepository.findAll();
        return movies.get(new Random().nextInt(movies.size()));
    }
}
