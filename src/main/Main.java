package main;


import model.Movie;
import rating.Rating;
import rating.RatingRegister;
import recommendation.MovieRecommendation;
import rating.Rating;

import java.util.*;


public class Main {


    public static void main(String[] args){

        Map<Integer, List<Movie>> ur = new HashMap<>();

        Map<Integer, Map<Integer, Rating>> mr = new HashMap<>();
        Movie mv = new Movie(3,"dawn of the dead");
        Movie mv2 = new Movie(5,"Titanic");
        List<Movie> list1 = new ArrayList<>();
        list1.add(mv);
        list1.add(mv2);
        ur.put(2, list1);



        Map<Integer, Rating> userRating = new HashMap<>();
        userRating.put(7,Rating.FIVE);
        userRating.put(9, Rating.FIVE);

        Map<Integer, Rating> userRating2 = new HashMap<>();
        userRating2.put(2, Rating.NOT_RATED);
        userRating2.put(8, Rating.TWO);
        Map<Integer, Map<Integer, Rating>> movieRat = new HashMap<>();

        movieRat.put(1,userRating );
        movieRat.put(2, userRating2);







        Rating rating = Rating.FIVE;
        System.out.println(rating);


        RatingRegister rr = new RatingRegister.RatingRegisterBuilder()
                .setUserMovies(ur)
                .setMovieRatings(movieRat)
                 .build();

        //System.out.println(rr.getUserMovies().toString());

        String x =rr.toString();
        System.out.println(x);
        //MovieRecommendation mR = new MovieRecommendation(rr);

        //String x = rr.toString();
        //System.out.println(x);

}
}
