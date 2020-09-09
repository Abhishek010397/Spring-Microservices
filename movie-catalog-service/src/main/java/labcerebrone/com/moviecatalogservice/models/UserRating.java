package labcerebrone.com.moviecatalogservice.models;

import java.util.List;

public class UserRating {

    public List<Ratings> getUserRating() {
        return userRating;
    }

    public void setUserRating(List<Ratings> userRating) {
        this.userRating = userRating;
    }

    private List<Ratings> userRating;

}
