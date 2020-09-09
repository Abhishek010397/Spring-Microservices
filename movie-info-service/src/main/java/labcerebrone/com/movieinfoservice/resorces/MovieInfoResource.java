package labcerebrone.com.movieinfoservice.resorces;

import labcerebrone.com.movieinfoservice.models.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieInfoResource {

    @RequestMapping("/{movieId}")
    public List<Movie> getMovie(@PathVariable("movieId") String movieId) {

        return Collections.singletonList(
                new Movie ("Titanic" , "7685")

        );

    }
}
