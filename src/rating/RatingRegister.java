package rating;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.Movie;
import model.User;


public class RatingRegister {

    private Map<Integer, List<Movie>> userMovies = new HashMap<>();  //UserID + Filme die gerated wurden
    private Map<Integer, Map<Integer, Rating>> movieRatings; //Taing eins FIlms : Movieid, <U_id, ratimg>
    private List<Movie> movies;
    private List<User> user;

    private RatingRegister(RatingRegisterBuilder rrb) {
        this.userMovies = rrb.userMovies;
        this.movieRatings = rrb.movieRatings;
        this.movies = rrb.movies;
        this.user = rrb.user;
    }


    public Map<Integer, List<Movie>> getUserMovies() {
        return userMovies;
    }

    public Map<Integer, Map<Integer, Rating>> getMovieRatings() {
        return movieRatings;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<User> getUser() {
        return user;
    }

    @Override
    public String toString(){
        int counter = 0;
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Integer,List<Movie>> outerValue : userMovies.entrySet()) {
            Integer u = outerValue.getKey();
            sb.append("\n");
            sb.append("User-ID: ");
            sb.append(u);
            sb.append(" | ");
            List<Movie> movieList = outerValue.getValue();
            for (Movie movie : movieList) {
                sb.append(movie.getTitle());
                sb.append(" | ");
}
            }sb.append("\n");
            for (Map.Entry<Integer, Map<Integer, Rating>> outerValuee : movieRatings.entrySet()) {
                Map<Integer, Rating> innerMap = outerValuee.getValue();
                Integer movieId = outerValuee.getKey();
                sb.append("\n");
                sb.append("Movie-ID: ");
                sb.append(movieId);
                sb.append(" --> ");
                    for (Map.Entry<Integer, Rating> x : innerMap.entrySet()) {
                        Integer uid = x.getKey();
                        Rating rating = x.getValue();
                        sb.append(" User-ID: ");
                        sb.append(uid);
                        sb.append(" | Rating: ");
                        sb.append(rating);
                        sb.append(" | | ");


                }
            }
        return sb.toString();
    }



    public static class RatingRegisterBuilder {

        private Map<Integer, List<Movie>> userMovies;  //Ratings von User + die bewerteten Filme
        private Map<Integer, Map<Integer, Rating>> movieRatings; //Taing eins FIlms : Movieid, <(U_id, ratimg>
        private List<Movie> movies;
        private List<User> user;




        public RatingRegisterBuilder setUserMovies(Map<Integer, List<Movie>> userMovies) {
            this.userMovies = userMovies;
            return this;
        }



        public RatingRegisterBuilder setMovieRatings(Map<Integer, Map<Integer, Rating>> movieRatings) {
            this.movieRatings = movieRatings;
            return this;
        }


        public RatingRegisterBuilder setMovies(List<Movie> movies) {
            this.movies = movies;
            return this;
        }


        public RatingRegisterBuilder setUser(List<User> user) {
            this.user = user;
            return this;
        }

        public RatingRegister build(){
            return new RatingRegister(this);
        }


    }
}