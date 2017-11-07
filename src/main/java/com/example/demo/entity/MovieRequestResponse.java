package com.example.demo.entity;

/**
 * Created by Joshua on 11/3/2017.
 */
public class MovieRequestResponse {
    private int movieRequestId;
    private int toUser;
    private String fromUser;
    private long timeSent;
    private int fromUserId;
    private String message;

    public int getToUser() {
        return toUser;
    }

    public int getMovieRequestId() {
        return movieRequestId;
    }

    public void setMovieRequestId(int movieRequestId) {
        this.movieRequestId = movieRequestId;
    }

    public void setToUser(int toUser) {
        this.toUser = toUser;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public long getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(long timeSent) {
        this.timeSent = timeSent;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
