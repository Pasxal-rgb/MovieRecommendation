package recommendation;
import rating.Rating;
import rating.Rating;
import rating.RatingRegister;

import java.util.Map;

public class MovieRecommendation {

    private Map<Integer, Map<Integer, Rating>> movieRatings;

    public MovieRecommendation(RatingRegister rg){
        try {
            if (rg != null) {
                this.movieRatings = rg.getMovieRatings();
            } else {
                System.out.println("Es wurde noch kein Rating-Register erstellt.");
            }
            if(this.movieRatings != null) System.out.println(movieRatings.toString());
        }catch(Exception e){
            System.out.println("Error " + e);
        }
    }

}
