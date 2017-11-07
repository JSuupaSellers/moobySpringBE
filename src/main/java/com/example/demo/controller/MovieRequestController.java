package com.example.demo.controller;

import com.example.demo.entity.MovieRequest;
import com.example.demo.entity.MovieRequestResponse;
import com.example.demo.service.IMovieRequestService;
import com.example.demo.service.IMoviesSeenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by the_s on 9/11/2017.
 */
@Controller
@RequestMapping("data")
public class MovieRequestController {
    @Autowired
    private IMovieRequestService movieRequestService;
    @Autowired
    IMoviesSeenService moviesSeenService;
    @GetMapping("movieRequests")
    public ResponseEntity<List<MovieRequest>> getAllMovieRequests(){
        List<MovieRequest> list = movieRequestService.getAllMovieRequests();
        return new ResponseEntity<List<MovieRequest>>(list, HttpStatus.OK);
    }

    @GetMapping("movieRequests/{id}")
    public ResponseEntity<List<MovieRequest>> getMovieRequestsFor(@PathVariable("id")Integer id){
        List<MovieRequest> list = movieRequestService.getAllMovieRequestsForUser(id);
        return new ResponseEntity<List<MovieRequest>>(list, HttpStatus.OK);
    }

    @PostMapping("movieRequest")
    public ResponseEntity<MovieRequestResponse> sendMovieRequest(@RequestBody MovieRequest request){
        boolean flag = movieRequestService.sendMovieRequest(request);
        MovieRequestResponse response = new MovieRequestResponse();

        if(!flag){
            return new ResponseEntity<>(response,HttpStatus.CONFLICT);
        }
        response.setTimeSent(request.getTimeSent().longValue());
        response.setMessage("Movie recommendation sent");

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("movieRequest/{id}")
    public ResponseEntity<MovieRequestResponse> deleteMovieRequest(@PathVariable("id")Integer id){
        boolean flag = movieRequestService.deleteMovieRequest(id);
        MovieRequestResponse response = new MovieRequestResponse();
        if(!flag){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        response.setMessage("request removed");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
