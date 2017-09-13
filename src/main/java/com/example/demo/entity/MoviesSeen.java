package com.example.demo.entity;

import javax.persistence.*;

/**
 * Created by the_s on 9/11/2017.
 */
@Entity
@Table(name = "movies_seen")
public class MoviesSeen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movies_id")
    private int movieID;

    @Column(name = "user_id")
    private int userId;
    @Column(name = "movie_id")
    private int movieId;
    @Column(name = "liked")
    private boolean liked;
    @Column(name = "faved")
    private boolean faved;

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isFaved() {
        return faved;
    }

    public void setFaved(boolean faved) {
        this.faved = faved;
    }
}
