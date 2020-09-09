package labcerebrone.com.moviecatalogservice.resources;


import labcerebrone.com.moviecatalogservice.models.CatalogItem;
import labcerebrone.com.moviecatalogservice.models.Movie;
import labcerebrone.com.moviecatalogservice.models.Ratings;
import labcerebrone.com.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired //consumer:- telling spring give me something
    private RestTemplate restTemplate;
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingdata/users/" + userId, UserRating.class);
        return ratings.getUserRating().stream().map(rating -> {
            // For each movieId, call movie info service and get details
            Movie movie = restTemplate.getForObject("http://localhost:8082/movie/" + rating.getMovieId(), Movie.class);
            // Put them all together
            return new CatalogItem(movie.getName(), "Best", rating.getRating());
        })
            .collect(Collectors.toList());



    }
}
