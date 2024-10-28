package recommendation;
import model.Genre;
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


    public Map<Genre, Integer> getUserFavouriteGenres(User user){
        Map<Genre, Integer> genreCount = new HashMap<>();
        List<Movie> movies = this.userMovies.get(user.getU_id());
        List<Genre> genreList = new ArrayList<>();
        if(movies != null){
            for(Movie movie : movies){
                Genre g = movie.getGenre();
                genreList.add(g);
            }

           int actionCount = Collections.frequency(genreList, Genre.ACTION);
           int dramaCount = Collections.frequency(genreList, Genre.DRAMA);
           int scienceFictionCount = Collections.frequency(genreList, Genre.SCIENCEFICTION);
           int westernCount = Collections.frequency(genreList, Genre.WESTERN);
           int komödienAcount = Collections.frequency(genreList, Genre.KOMÖDIE);
           int misteryCount = Collections.frequency(genreList, Genre.MISTERY);

           genreCount.put(Genre.ACTION,actionCount);
           genreCount.put(Genre.DRAMA, dramaCount);
           genreCount.put(Genre.SCIENCEFICTION, scienceFictionCount);
           genreCount.put(Genre.WESTERN, westernCount);
           genreCount.put(Genre.KOMÖDIE, komödienAcount);
           genreCount.put(Genre.MISTERY, misteryCount);

            }return genreCount;
        }

        public int getGenreAffinityScore(User user1, User user2){
        return 0;
        }
    }


