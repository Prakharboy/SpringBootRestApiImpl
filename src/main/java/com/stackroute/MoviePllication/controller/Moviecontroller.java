package com.stackroute.MoviePllication.controller;


import com.stackroute.MoviePllication.domain.Movie;
import com.stackroute.MoviePllication.movieService.MovieInterfaceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class Moviecontroller {

    MovieInterfaceImpl movieInterface;

    @Autowired
    public Moviecontroller(MovieInterfaceImpl movieInterface) {
        this.movieInterface = movieInterface;
    }

    @PostMapping(value = "movie")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie)
    {
        ResponseEntity responseEntity;

        try {

            movieInterface.saveMovie(movie);
            responseEntity=new ResponseEntity<String>("succesfully created", HttpStatus.CREATED);
        }
        catch (Exception ex)
        {
            responseEntity=new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }



        return responseEntity;
    }

    @PutMapping("/movie")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie)
    {
        ResponseEntity<String> responseEntity;
        try{
            Movie movie1=movieInterface.updateMovie(movie);
           responseEntity= new ResponseEntity<String>("updated",HttpStatus.OK);
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<String>("failed to update",HttpStatus.CONFLICT);
            return responseEntity;
        }
        return responseEntity;
    }


    @DeleteMapping("/movie")
    public ResponseEntity<List<Movie>> deleteMovie(@RequestBody Movie movie)
    {

        if(movieInterface.getMovieById(movie.getMovieId())==null)
            return  new ResponseEntity<List<Movie>>(movieInterface.getAllMovies(),HttpStatus.CONFLICT);
        try
        {movieInterface.deleteMovie(movie);}
        catch (Exception e)
        {
            ResponseEntity responseEntity=new ResponseEntity<String>("no such movie",HttpStatus.CONFLICT);
            return responseEntity;
        }

        return  new ResponseEntity<List<Movie>>(movieInterface.getAllMovies(),HttpStatus.OK);



    }
    @DeleteMapping("/movie/{id}")
    public ResponseEntity<List<Movie>> deleteMoviebyId(@PathVariable int id)
    {

try {


        movieInterface.deleteMovieById(id);}
catch (Exception e)
{
   ResponseEntity responseEntity=new ResponseEntity<String>("failed to update",HttpStatus.CONFLICT);
    return responseEntity;
}

        return  new ResponseEntity<List<Movie>>(movieInterface.getAllMovies(),HttpStatus.OK);
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int movieId)
    {
        try {

        return new ResponseEntity<Movie>(movieInterface.getMovieById(movieId),HttpStatus.OK);}
        catch (Exception e)
        {
            ResponseEntity responseEntity=new ResponseEntity<String>("this movie id doesn't exists",HttpStatus.CONFLICT);
            return responseEntity;
        }


    }

    @GetMapping(value = "/movie")
    public ResponseEntity<List<Movie>> getAllMovies()
    {
try{
        return  new ResponseEntity<List<Movie>>(movieInterface.getAllMovies(),HttpStatus.OK);}
catch (Exception e)
{
    ResponseEntity responseEntity=new ResponseEntity<String>("no movies found",HttpStatus.CONFLICT);
    return responseEntity;
}
    }

}
