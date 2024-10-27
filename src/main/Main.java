package main;

import model.User;
import model.Movie;
import rating.Rating;
import rating.RatingRegister;
import recommendation.MovieRecommendation;
import rating.Rating;

import java.util.*;
import java.util.stream.Collectors;


public class Main {


    public static void main(String[] args) {

        List<Movie> movies = new ArrayList<>(Arrays.asList(
                new Movie(1, "Inception", "Western"),
                new Movie(2, "Titanic", "Drama")
        ));

        Map<Integer, Map<Integer, Rating>> movieRatings = new HashMap<>();
        Map<Integer, Rating> userRatings = new HashMap<>();
        userRatings.put(1, Rating.FIVE);
        userRatings.put(2, Rating.FOUR);
        userRatings.put(3, Rating.TWO );

        movieRatings.put(1, userRatings);  // movie ID 1 (Inception) has some ratings

        RatingRegister rrg = new RatingRegister.RatingRegisterBuilder()
                .setMovies(movies)
                .setMovieRatings(movieRatings)
                .build();


        MovieRecommendation mRec = new MovieRecommendation(rrg);

        List<Movie> highestRatedMovies = rrg.getHighestRating();

        for (Movie movie : highestRatedMovies) {
            System.out.println("Highest rated movie: ID und Titel: " +movie.getId()+", "+ movie.getTitle());
        }

        double  d  = rrg.getAverageRating(movies.get(0));
        System.out.println("Durchschnittliche Bewerung für den Film" + movies.get(0).getTitle()+": "+d);


    }
}

     /*   Map<Integer, List<Movie>> ur = new HashMap<>();

        Map<Integer, Map<Integer, Rating>> mr = new HashMap<>();
        Movie mv = new Movie(3, "dawn of the dead");
        Movie mv2 = new Movie(5, "Titanic");
        Movie mv3 = new Movie(8, "Hanswurst");
        List<Movie> list1 = new ArrayList<>();
        list1.add(mv);
        list1.add(mv2);
        ur.put(2, list1);


        Map<Integer, Rating> userRating = new HashMap<>();
        userRating.put(7, Rating.FIVE);
        userRating.put(9, Rating.FIVE);

        Map<Integer, Rating> userRating2 = new HashMap<>();
        userRating2.put(2, Rating.NOT_RATED);
        userRating2.put(8, Rating.TWO);
        Map<Integer, Map<Integer, Rating>> movieRat = new HashMap<>();

        User userer = new User(2, "Hans");
        List<User> user = new ArrayList<>();
        user.add(userer);

        List<Movie> liste = new ArrayList<>();
        liste.add(mv);
        Map<Integer, List<Movie>> filme = new HashMap<>();
        filme.put(1, liste);

        movieRat.put(1, userRating);
        movieRat.put(2, userRating2);
        RatingRegister rg = new RatingRegister.RatingRegisterBuilder().setUser(user).setUserMovies(filme).setMovieRatings(movieRat).build();

        Map<Integer, List<Movie>> userMovies = rg.getUserMovies();
        if (userMovies != null) System.out.println("success");

        MovieRecommendation mRec = new MovieRecommendation(rg);

        Map<Integer, List<Movie>> userMovies2 = mRec.getUserMovies();
        if (userMovies2 != null) System.out.println("success2");

        mRec.rateMovie(userer, mv, Rating.FIVE);

        System.out.println(rg.toString());

        User usur = new User(89, "Franz");

        mRec.addUserovie(usur, mv3);

        Map<Integer, List<Movie>> userMovieListe = rg.getUserMovies();

        Movie movie = new Movie(30, "Haus der 100 Leichen");
        mRec.rateMovie(userer, movie, Rating.FIVE);


        List<Movie> moreMovies = new ArrayList<>(Arrays.asList(new Movie(3, "Avatar"), new Movie(4, "Titanic")));


        RatingRegister rrg = new RatingRegister.RatingRegisterBuilder().setUser(user).setUserMovies(filme).setMovieRatings(movieRat).setMovies(moreMovies)
                .build();


        List<Movie> standardMovieList = rrg.getMovies();
        standardMovieList.addAll(moreMovies);


        for (Movie m : standardMovieList) {
            mRec.rateMovie(userer, m, Rating.FIVE);
        }

        List<Movie> highestMoviesRated = mRec.getHighestRating();

        for (Movie m : highestMoviesRated) {
            System.out.println("\n hcöhste ratings: " + m.getTitle());
        }

    }
}

         //rg.toString();
/*

        for (Map.Entry<Integer, List<Movie>> entry : mRec.getUserMovies().entrySet()) {
            Integer userId = entry.getKey();  // Benutzer-ID
            List<Movie> movies = entry.getValue();  // Liste von Filmen für diesen Benutzer

            System.out.println("Benutzer-ID: " + userId);
            System.out.println("Filme:");
            for (Movie movie : movies) {
                System.out.println("- " + movie.getTitle());  // Annahme: Movie hat eine getTitle() Methode
            }
        }





        //System.out.println(rr.getUserMovies().toString());

        String x =rg.toString();
        System.out.println(x);
        //MovieRecommendation mR = new MovieRecommendation(rr);

        //String x = rr.toString();
        //System.out.println(x);



}
}*/
