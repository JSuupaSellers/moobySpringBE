package com.example.demo.entity;

/**
 * Created by Joshua on 9/27/2017.
 */
public class MovieSeenResponse {
    private boolean movieDeleted;
    private boolean movieAdded;
    private boolean movieUpdated;

    public boolean isMovieUpdated() {
        return movieUpdated;
    }

    public void setMovieUpdated(boolean movieUpdated) {
        this.movieUpdated = movieUpdated;
    }

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
