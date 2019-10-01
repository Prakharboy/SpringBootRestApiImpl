package com.stackroute.MoviePllication.repository;

import com.stackroute.MoviePllication.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
}
