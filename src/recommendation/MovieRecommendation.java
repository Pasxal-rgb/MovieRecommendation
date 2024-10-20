package recommendation;
import model.Movie;
import model.User;
import rating.Rating;
import rating.Rating;
import rating.RatingRegister;

import java.util.*;
import java.util.function.Predicate;

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
            }else{
                System.out.println("Kein User vorhanden.");
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


    //TO-DO: Fall: User hat noch keine Filme bewertet und innerMap muss neu erstellt werden für den User.
    public void rateMovie(User user, Movie movie, Rating rating){
        if(!this.movieRatings.isEmpty()) {
            boolean alreadyRatedByUser = this.movieRatings.get(user.getU_id()).containsValue(movie);
            if (!alreadyRatedByUser) {

                Map<Integer, Rating> innerMap = this.movieRatings.get(user.getU_id());
                innerMap.put(movie.getId(), rating);
                this.movieRatings.put(user.getU_id(), innerMap);

            } else {
                System.out.println("Der User " + user.getName() + " hat für den Film " + movie.getTitle() + " bereits ein Rating von " + rating.toString() + " abgegeben.");
            }

        }else{
            Map<Integer,Rating> innerMap = new HashMap<>();
            innerMap.put(movie.getId(), rating);
            this.movieRatings.put(user.getU_id(),innerMap);

        }

    }
}
