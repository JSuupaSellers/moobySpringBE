package com.example.demo.controller;

import com.example.demo.entity.MovieRequest;
import com.example.demo.service.IMovieRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by the_s on 9/11/2017.
 */
@Controller
@RequestMapping("data")
public class MovieRequestController {
    @Autowired
    private IMovieRequestService movieRequestService;

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
    public ResponseEntity<Boolean> sendMovieRequest(@RequestBody MovieRequest request){
        boolean flag = movieRequestService.sendMovieRequest(request);

        if(!flag){
            return new ResponseEntity<Boolean>(false,HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @DeleteMapping("movieRequest/{id}/{from}")
    public ResponseEntity<Boolean> deleteMovieRequest(@PathVariable("id")Integer id,@PathVariable("from") Integer from){
        boolean flag = movieRequestService.deleteMovieRequest(id,from);
        if(!flag){
            return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }
}
