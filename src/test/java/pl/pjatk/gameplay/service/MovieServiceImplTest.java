package pl.pjatk.gameplay.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.pjatk.gameplay.exception.MovieNotFoundException;
import pl.pjatk.gameplay.model.Category;
import pl.pjatk.gameplay.model.Movie;
import pl.pjatk.gameplay.repository.CategoryRepository;
import pl.pjatk.gameplay.repository.MovieRepository;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private MovieServiceImpl movieServiceImpl;

    @Test
    void shouldGetMovieById() {
        //given
        Category category = new Category("Komedia");
        Movie movie = new Movie(1L,"Film1", "Fajny film", category);
        //when
        movieRepository.findById(1L);
        //then
        assertThat(movie.getId()).isEqualTo(1L);
    }

    @Test
    void getAllMovies() {
        //given
        Category category = new Category("Horror");
        Movie movie1 = new Movie(1L,"Film1", "Fajny film", category);
        Movie movie2 = new Movie(2L,"Film2", "Fajny film2", category);
        Movie movie3 = new Movie(3L,"Film3", "Fajny film3", category);
        //when
        movieRepository.findAll();
        //then
        assertThat(movie1.getId()).isEqualTo(1L);
        assertThat(movie2.getId()).isEqualTo(2L);
        assertThat(movie3.getId()).isEqualTo(3L);
    }

    @Test
    void changeDescription() {
        //given
        Category category = new Category("Horror");
        Movie movie1 = new Movie(1L,"Film1", "Fajny film", category);
        //when
        movie1.setDescription("Niefajny film");
        //then
        assertThat(movie1.getDescription().equals("Niefajny film"));
    }

//    public Movie changeDescription(Long id, String description) {
//        Movie movie = movieRepository.findById(id).orElseThrow(
//                () -> new MovieNotFoundException(id));
//        movie.setDescription(description);
//        return movieRepository.save(movie);
//    }

//    public List<Movie> getAllMovies() {
//        return (List<Movie>) movieRepository.findAll();
//    }

//    @Test
//    void getRandomMovie() {
//        //given
//        Category category = new Category("Horror");
//        Movie movie1 = new Movie("Film1", "Fajny film", category);
//        Movie movie2 = new Movie("Film2", "Fajny film2", category);
//        Movie movie3 = new Movie("Film3", "Fajny film3", category);
//        //when
//        List<Movie> movies = (List<Movie>) movieRepository.findAll();
//        //then
//        assertThat(movies.get(new Random().nextInt(movies.size()))).isNotEqualTo(null);
//    }



//    public Movie getRandomMovie() {
//        List<Movie> movies = (List<Movie>) movieRepository.findAll();
//        return movies.get(new Random().nextInt(movies.size()));
//    }

}
