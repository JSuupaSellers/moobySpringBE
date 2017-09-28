package com.example.demo.dao;

import com.example.demo.entity.MoviesSeen;

import java.util.List;

/**
 * Created by the_s on 9/11/2017.
 */
public interface IMoviesSeenDAO {
    List<MoviesSeen> getAllMoviesSeen();
    List<MoviesSeen> getMoviesSeenFor(int userId);
    void addMovieToSeen(MoviesSeen movie);
    void updateMovieSeen(MoviesSeen movie);
    void deleteFromSeenList(int movieId);
    boolean movieListContainsMovie(int movieId);
    MoviesSeen getMovieForUserWithId(int movieId, int userId);
    void delete(MoviesSeen moviesSeen);
}
