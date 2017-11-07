package com.example.demo.service;

import com.example.demo.dao.IMoviesSeenDAO;
import com.example.demo.entity.MoviesSeen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by the_s on 9/11/2017.
 */
@Service
public class MoviesSeenService implements IMoviesSeenService {
    @Autowired
    private IMoviesSeenDAO moviesSeenDAO;

    @Override
    public List<MoviesSeen> getAllMoviesSeen() {
        return moviesSeenDAO.getAllMoviesSeen();
    }

    @Override
    public List<MoviesSeen> getAllMoviesSeenFor(int userId) {
        return moviesSeenDAO.getMoviesSeenFor(userId);
    }

    @Override
    public boolean addMovieToList(MoviesSeen movie) {
        if(moviesSeenDAO.movieListContainsMovie(movie)){
            return false;
        }else{
            moviesSeenDAO.addMovieToSeen(movie);
            return true;
        }
    }

    @Override
    public boolean updateMovieSeen(MoviesSeen movie) {
        return moviesSeenDAO.updateMovieSeen(movie);
    }

    @Override
    public boolean deleteMovieFromList(MoviesSeen movie) {
        moviesSeenDAO.deleteFromSeenList(movie);
        return true;
    }

    @Override
    public MoviesSeen getMovieSeen(int movieId, String userId) {
        return moviesSeenDAO.getMovieForUserWithId(movieId,userId);
    }
    @Override
    public void deleteMovieSeen(MoviesSeen moviesSeen){
        moviesSeenDAO.delete(moviesSeen);
    }
}
