package com.example.demo.service;

import com.example.demo.dao.IMovieRequestDAO;
import com.example.demo.entity.MovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by the_s on 9/11/2017.
 */
@Service
public class MovieRequestService implements IMovieRequestService {
    @Autowired
    private IMovieRequestDAO movieRequestDAO;

    @Override
    public List<MovieRequest> getAllMovieRequests() {
        return movieRequestDAO.getAllMovieRequests();
    }

    @Override
    public List<MovieRequest> getAllMovieRequestsForUser(int userId) {
        return movieRequestDAO.getMovieRequestsForUserId(userId);
    }

    @Override
    public MovieRequest getSingleMovieRequest(int userId, int fromUserId) {
        return movieRequestDAO.getSingleMovieRequest(userId,fromUserId);
    }

    @Override
    public boolean sendMovieRequest(MovieRequest request) {
        System.out.println("Service value for from user :: " + request.getFromUser());
        if(movieRequestDAO.movieRequestExists(request.getUserId(),request.getFromUser())){
            return false;
        }else {
            movieRequestDAO.addMovieRequest(request);
            return true;
        }
    }

    @Override
    public boolean deleteMovieRequest(int userId, int fromUserId) {

        movieRequestDAO.removeMovieRequest(userId, fromUserId);
        if(movieRequestDAO.movieRequestExists(userId, fromUserId)){
            return false;
        }else {
            return true;
        }
    }
}
