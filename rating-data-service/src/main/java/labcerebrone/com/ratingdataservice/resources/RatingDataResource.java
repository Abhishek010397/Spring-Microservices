package labcerebrone.com.ratingdataservice.resources;

import labcerebrone.com.ratingdataservice.models.Ratings;
import labcerebrone.com.ratingdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingdata")
public class RatingDataResource {

    @RequestMapping("/{movieId}")
    public Ratings getRating(@PathVariable("movieId") String movieId) { return new Ratings(movieId, 4); }

    @RequestMapping("users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId) {
        List<Ratings> rating = Arrays.asList(
                new Ratings("1234", 4),
                new Ratings("5678", 3)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(rating);
        return userRating;
    }
}
