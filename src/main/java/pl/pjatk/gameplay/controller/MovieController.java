package pl.pjatk.gameplay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.gameplay.dto.MovieDto;
import pl.pjatk.gameplay.model.Movie;
import pl.pjatk.gameplay.service.MovieService;

import java.util.List;

@RestController
public class  MovieController {
    private MovieService service;

    @Autowired
    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("/movie")
    public List<Movie> getAllMovies(){
        return service.getAllMovies();
    }

    @GetMapping("/movie/{id}")
    public Movie getMovieById(@PathVariable Long id){
        return service.getMovieById(id);
    }

    @GetMapping("/category/{name}/movies")
    public List<Movie> getMoviesByCategory(@PathVariable String name){
        return service.getMoviesByCategory(name);
    }
    @GetMapping("/movie/random")
    public Movie getRandomMovie(){
        return service.getRandomMovie();
    }
    @PostMapping("/movie/add")
    public Movie addMovie(@RequestBody MovieDto movieDto){
        return service.addMovie(movieDto);
    }
    @PutMapping("/movie/{id}/update-desc")
    public Movie updateDescription(@PathVariable Long id, @RequestParam String description){
        return service.changeDescription(id,description);
    }
    @DeleteMapping("/movie/{id}/delete")
    public void deleteMovie(@PathVariable Long id){
        service.deleteMovie(id);
    }
}
