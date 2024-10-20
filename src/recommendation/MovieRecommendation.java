package recommendation;
import model.Movie;
import model.User;
import rating.Rating;
import rating.Rating;
import rating.RatingRegister;

import java.util.*;
import java.util.function.Predicate;


//List User und List Movie hinzufügen, in den Konstruktor und Methoden aktualisieren,


public class MovieRecommendation {

    private Map<Integer, Map<Integer, Rating>> movieRatings;
    private Map<Integer, List<Movie>> userMovies;

    public MovieRecommendation(RatingRegister rg){
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

    public Map<Integer, Map<Integer, Rating>> getMovieRatings() {
        return movieRatings;
    }

    public void setMovieRatings(Map<Integer, Map<Integer, Rating>> movieRatings) {
        this.movieRatings = movieRatings;
    }

    public Map<Integer, List<Movie>> getUserMovies() {
        return userMovies;
    }

    public void setUserMovies(Map<Integer, List<Movie>> userMovies) {
        this.userMovies = userMovies;
    }

    public void addUserovie(User user, Movie movie){
        try {
            if(!this.userMovies.isEmpty()) {
                boolean movieInList = this.userMovies.values().stream()
                        .flatMap(List::stream) //Aus der Liste wird ein Strom von   Strings
                        .anyMatch(x -> x.equals(movie));
            }
            boolean userInList = this.userMovies.containsKey(user.getU_id());

            if (userInList && !userMovies.isEmpty()) {
                this.userMovies.get(user.getU_id()).add(movie);
            }
            if(!userInList && !userMovies.isEmpty()){
                List<Movie> mList = new ArrayList<>();
                mList.add(movie);
                this.userMovies.put(user.getU_id(), mList);
            }

            }catch(Exception e){
            System.out.println(e);


        }
    }

    public void userMovieListToString() {
        this.userMovies.entrySet().stream()
                .forEach(entry -> {
                    Integer uid = entry.getKey();
                    List<Movie> mvListe = entry.getValue();
                    System.out.println("UID: " + uid);

                    mvListe.stream()
                            .forEach(movie -> System.out.println(movie.getTitle()));

                });
    }


    public Movie recommendTopRated(){
        return null;
    }


    public Movie getHighestRating(Map<Integer,Rating> innerMap){
        Rating maxRating = null;
        Movie maxScoring = null;
        int counter = 0;
        int mId = 0;
        for(Map.Entry<Integer, Rating> entry : innerMap.entrySet()){
            Rating currentRating = entry.getValue();
            if(Integer.parseInt(currentRating.toString()) > Integer.parseInt(maxRating.toString())) {
                maxRating = currentRating;
            }
            if(entry.getValue().equals(maxRating)){
                mId = entry.getKey();
                counter++;

            }
            if(entry.getKey() == mId){
                // moviesList
            }


        }
        return null;
    }





    public void rateMovie(User user, Movie movie, Rating rating){
        Map <Integer, Rating> innerMap = this.movieRatings.get(user.getU_id());

        if(innerMap == null){
            Map<Integer, Rating> freshInnerMap = new HashMap<>();
            freshInnerMap.put(movie.getId(), rating);
           this.movieRatings.put(user.getU_id(), freshInnerMap);
            System.out.println("User und dessen liste wurden erstellt.");
        }
        if(innerMap != null && !innerMap.containsKey(movie.getId())){
            innerMap.put(movie.getId(), rating);
            this.movieRatings.put(user.getU_id(), innerMap);
            System.out.println("Rating wurde in bestehende innerMap hinzugefügt.");
        } else if(innerMap.containsKey(movie.getId()) && this.movieRatings.containsKey(user.getU_id())){
            System.out.println("USer "+ user.getName()+ " hat den Film mit der ID "+movie.getTitle()+" breits mit dem Rating "+ this.movieRatings.get(user.getU_id()).get(movie.getId()));
        }
    }
}
