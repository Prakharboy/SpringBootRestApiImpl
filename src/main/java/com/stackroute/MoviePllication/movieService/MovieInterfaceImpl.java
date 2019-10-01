package com.stackroute.MoviePllication.movieService;

import com.stackroute.MoviePllication.domain.Movie;
import com.stackroute.MoviePllication.repository.MovieRepository;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieInterfaceImpl implements MovieInterface {

MovieRepository movieRepository;

@Autowired
    public MovieInterfaceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        Movie savedMovie=this.movieRepository.save(movie);
        return savedMovie;

    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movieList=movieRepository.findAll();
        return movieList;
    }

    @Override
    public void deleteMovie(Movie movie) {

        movieRepository.delete(movie);
    }

    @Override
    public void deleteMovieById(int movieId) {

      Movie movie=getMovieById(movieId);
      deleteMovie(movie);



    }

    @Override
    public Movie getMovieById(int movieId) {

      List<Movie> movieList=movieRepository.findAll(); //here

        for(int i=0;i<movieList.size();i++)
        {
            if(movieList.get(i).getMovieId()==movieId)
            {return movieList.get(i);}
        }

        return null;

}

    @Override
    public Movie updateMovie(Movie movie) {
        movieRepository.save(movie);
        return movie;
    }


}
