package com.example.demo.dao;

import com.example.demo.entity.MovieRequest;

import java.util.List;

/**
 * Created by the_s on 9/11/2017.
 */
public interface IMovieRequestDAO {
    List<MovieRequest> getAllMovieRequests();
    List<MovieRequest> getMovieRequestsForUserId(int userId);
    MovieRequest getSingleMovieRequest(int userId, int fromUserId);
    void addMovieRequest(MovieRequest request);
    boolean movieRequestExists(int userId, int fromUserId);
    void removeMovieRequest(int userId, int fromUserId);
}
