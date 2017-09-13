package com.example.demo.service;

import com.example.demo.entity.MoviesSeen;

import java.util.List;

/**
 * Created by the_s on 9/11/2017.
 */
public interface IMoviesSeenService {
    List<MoviesSeen> getAllMoviesSeen();
    List<MoviesSeen> getAllMoviesSeenFor(int userId);
    boolean addMovieToList(MoviesSeen movie);
    void updateMovieSeen(MoviesSeen movie);
    boolean deleteMovieFromList(int movieId);
}
