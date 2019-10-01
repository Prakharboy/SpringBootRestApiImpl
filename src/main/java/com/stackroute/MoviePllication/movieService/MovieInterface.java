package com.stackroute.MoviePllication.movieService;

import com.stackroute.MoviePllication.domain.Movie;

import java.util.List;

public interface MovieInterface {


    public Movie saveMovie(Movie movie);

    public List<Movie> getAllMovies();

    public  void deleteMovie(Movie movie);

    public void deleteMovieById(int movieId);

    public  Movie getMovieById(int movieId);

    public  Movie updateMovie(Movie movie);
}
