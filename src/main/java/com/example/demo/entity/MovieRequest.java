package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by the_s on 9/11/2017.
 */
@Entity
@Table(name = "movie_requests")
public class MovieRequest implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_id")
    private int movieRequestId;

    @Column(name = "user_id")
    private int userId;

    @Column(name="from_user")
    private int fromUserId;

    @Column(name="movie_id")
    private int movieId;

    @Column(name="time_sent")
    private BigInteger timeSent;

    public int getMovieRequestId() {
        return movieRequestId;
    }

    public void setMovieRequestId(int movieRequestId) {
        this.movieRequestId = movieRequestId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFromUser() {
        return fromUserId;
    }

    public void setFromUser(int fromUser) {
        this.fromUserId = fromUser;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public BigInteger getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(BigInteger timeSent) {
        this.timeSent = timeSent;
    }
}
