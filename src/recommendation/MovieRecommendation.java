package recommendation;
import model.Movie;
import model.User;
import rating.Rating;
import rating.Rating;
import rating.RatingRegister;

import java.util.*;
import java.util.function.Predicate;

//Instanzvariable RatingRegister--->sämtliche Methoden synchrobisieren in Bezug auf beide Klassen und deren Listen
//List User und List Movie hinzufügen, in den Konstruktor und Methoden aktualisieren,
//Verantwortlichkeiten überdenken, RATing Register und Recommendation zum empfehlen
//Aufräumen.


public class MovieRecommendation {

    private Map<Integer, Map<Integer, Rating>> movieRatings;
    private Map<Integer, List<Movie>> userMovies;
    public RatingRegister rg;

    public MovieRecommendation(RatingRegister rg){
        this.rg = rg;
        try {
            if (rg != null && rg.getMovieRatings() != null) {
                this.movieRatings = rg.getMovieRatings();
            }
            if(rg != null && rg.getUserMovies() != null){
                this.userMovies = rg.getUserMovies();
            }
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
    }



}
