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
    boolean updateMovieSeen(MoviesSeen movie);
    void deleteFromSeenList(MoviesSeen moviesSeen);
    boolean movieListContainsMovie(MoviesSeen movie);
    MoviesSeen getMovieForUserWithId(int movieId, int userId);
    void delete(MoviesSeen moviesSeen);
}
