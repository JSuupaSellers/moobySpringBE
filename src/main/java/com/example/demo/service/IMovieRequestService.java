package com.example.demo.service;

import com.example.demo.entity.MovieRequest;

import java.util.List;

/**
 * Created by the_s on 9/11/2017.
 */
public interface IMovieRequestService {
    List<MovieRequest> getAllMovieRequests();
    List<MovieRequest> getAllMovieRequestsForUser(int userId);
    MovieRequest getSingleMovieRequest(int userId, int fromUserId);
    boolean sendMovieRequest(MovieRequest request);
    boolean deleteMovieRequest(int movieRequestId);
}
