package com.example.demo.entity;

/**
 * Created by Joshua on 9/27/2017.
 */
public class MovieSeenResponse {
    private boolean movieDeleted;
    private boolean movieAdded;

    public boolean isMovieDeleted() {
        return movieDeleted;
    }

    public void setMovieDeleted(boolean movieDeleted) {
        this.movieDeleted = movieDeleted;
    }

    public boolean isMovieAdded() {
        return movieAdded;
    }

    public void setMovieAdded(boolean movieAdded) {
        this.movieAdded = movieAdded;
    }
}
