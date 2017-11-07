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
    boolean updateMovieSeen(MoviesSeen movie);
    boolean deleteMovieFromList(MoviesSeen movie);
    MoviesSeen getMovieSeen(int movieId, String userId);
    void deleteMovieSeen(MoviesSeen moviesSeen);
}
