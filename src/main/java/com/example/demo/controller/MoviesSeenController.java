package com.example.demo.controller;

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
    public ResponseEntity<Boolean> addMovieSeen(@RequestBody MoviesSeen movie){
        boolean flag = moviesSeenService.addMovieToList(movie);

        if(!flag){
            return new ResponseEntity<Boolean>(false,HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PutMapping("movieSeen")
    public ResponseEntity<MoviesSeen> updateMovieSeen(@RequestBody MoviesSeen movie){
        moviesSeenService.updateMovieSeen(movie);
        return new ResponseEntity<MoviesSeen>(movie, HttpStatus.OK);
    }

    @DeleteMapping("movieSeen/{id}")
    public ResponseEntity<Boolean> deleteMovieSeen(@PathVariable("id")Integer id){
        boolean flag = moviesSeenService.deleteMovieFromList(id);
        if(!flag){
            return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }
}
