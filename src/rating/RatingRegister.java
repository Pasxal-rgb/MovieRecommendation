package rating;
import java.util.*;
import java.util.stream.Collectors;

import model.Movie;
import model.User;


public class RatingRegister {

    private Map<Integer, List<Movie>> userMovies = new HashMap<>();  //UserID + Filme die gerated wurden
    private Map<Integer, Map<Integer, Rating>> movieRatings; //Taing eins FIlms : Movieid, <U_id, ratimg>
    private List<Movie> movies = new ArrayList<>();
    private List<User> user;

    private RatingRegister(RatingRegisterBuilder rrb) {
        this.userMovies = rrb.userMovies;
        this.movieRatings = rrb.movieRatings;
        this.movies = rrb.movies;
        this.user = rrb.user;
    }

    //Methoden zum Hinzufügen von filmen und co


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

    public Movie recommendTopRated(){
        return null;
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
            System.out.println("USer "+ user.getName()+ " hat den Film mit der ID "+movie.getTitle()+" breits mit dem Rating "+ this.movieRatings.get(user.getU_id()).get(movie.getId())+ " bewertet,");
        }
    }

    public double getAverageRating(Movie movie){
        List<Rating> movieRatingsForSingleMovie = new ArrayList<>();
        double sum = 0.0;
        double ratingCount = 0.0;
        if(this.movieRatings != null){
            Set<Map.Entry<Integer, Map<Integer, Rating>>> innerMovieRatingsMap1 = this.movieRatings.entrySet();
            System.out.println("Movie ID to filter: " + movie.getId());
            innerMovieRatingsMap1.forEach(entry -> {
                System.out.println("Checking Movie ID: " + entry.getKey());
            });
        Set<Map.Entry<Integer, Map<Integer, Rating>>> innerMovieRatingsMap = this.movieRatings.entrySet();
        List<Rating> ratingForSingleMovie = innerMovieRatingsMap.stream()
                .filter(entry -> entry.getKey().equals(movie.getId()))
                .flatMap(
                        entry -> entry.getValue().values().stream())
                .filter(Rating::isRated)
                .toList();

        for(Rating r: ratingForSingleMovie) {
            ratingCount++;
            sum += r.getValue();
            System.out.println("Rating Value: " + r.getValue()); // Ausgabe des Werts

        }

            System.out.println("Sum: " + sum);
            System.out.println("Rating Count: " + ratingCount);
        }return ratingCount > 0 ? (double) Math.round((sum / (double) ratingCount) * 100) /100 : 0;
    }


    public List<Movie> getHighestRating(){
        Rating maxRating = Rating.ONE;
        Rating currentRating = null;
        List<Movie> mVList = new ArrayList<>();
        List<Integer> mId = new ArrayList<>();

        for(Map.Entry<Integer, Map<Integer, Rating>> entry : this.getMovieRatings().entrySet()) {
            Map<Integer, Rating> subs = entry.getValue();
            for (Map.Entry<Integer, Rating> ratVal : subs.entrySet()) {
                currentRating = ratVal.getValue();
                if (currentRating.compareTo(maxRating) > 0) {
                    maxRating = currentRating;
                }

            }

            if (subs.containsValue(maxRating)) {
                mId.add(entry.getKey());
            }
        }
        int i = 0;
        List<Movie> movies = this.getMovies();
        if(movies != null) {
            for (Movie mv : movies) {
                if (i < mId.size() && mv.getId() == mId.get(i)) {
                    mVList.add(mv);
                }
                i++;
            }
            return mVList;
        }else{
            return new ArrayList<>();
        }
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

