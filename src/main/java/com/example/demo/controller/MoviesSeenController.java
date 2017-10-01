package com.example.demo.controller;

import com.example.demo.entity.MovieSeenResponse;
import com.example.demo.entity.MoviesSeen;
import com.example.demo.service.IMoviesSeenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by the_s on 9/11/2017.
 */
@Controller
@RequestMapping("data")
public class MoviesSeenController {
    @Autowired
    private IMoviesSeenService moviesSeenService;

    @GetMapping("moviesSeen")
    public ResponseEntity<List<MoviesSeen>> getAllMoviesSeen(){
        List<MoviesSeen> list = moviesSeenService.getAllMoviesSeen();
        return new ResponseEntity<List<MoviesSeen>>(list, HttpStatus.OK);
    }

    @GetMapping("moviesSeen/{id}")
    public ResponseEntity<List<MoviesSeen>> getMoviesSeenFor(@PathVariable("id")Integer id){
        List<MoviesSeen> list = moviesSeenService.getAllMoviesSeenFor(id);
        return new ResponseEntity<List<MoviesSeen>>(list, HttpStatus.OK);
    }

    @PostMapping("movieSeen")
    public ResponseEntity<MoviesSeen> addMovieSeen(@RequestBody MoviesSeen movie){
        boolean flag = moviesSeenService.addMovieToList(movie);
        MoviesSeen res;
        if(!flag){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        res = moviesSeenService.getMovieSeen(movie.getMovieId(),movie.getUserId());
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @PutMapping("movieSeen")
    public ResponseEntity<MovieSeenResponse> updateMovieSeen(@RequestBody MoviesSeen movie){
        MovieSeenResponse response = new MovieSeenResponse();
        if(moviesSeenService.updateMovieSeen(movie)){

            response.setMovieUpdated(true);
            return new ResponseEntity<MovieSeenResponse>(response,HttpStatus.OK);
        }
        response.setMovieUpdated(false);
        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
    }

    @DeleteMapping("movieSeen")
    public ResponseEntity<MovieSeenResponse> deleteMovieSeen(@RequestBody MoviesSeen movie){
        MovieSeenResponse response = new MovieSeenResponse();
        moviesSeenService.deleteMovieFromList(movie);
//        if(!flag){
//            response.setMovieDeleted(false);
//            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
//        }
        response.setMovieDeleted(true);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
