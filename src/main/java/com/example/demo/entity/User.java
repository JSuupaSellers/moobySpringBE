package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Joshua on 9/4/2017.
 */
@Entity
@Table(name="users")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "numMoviesSeen")
    private int numMoviesSeen;
    @Column(name = "numMoviesFav")
    private int numMoviesFav;
    @Column(name = "numFriends")
    private int numFriends;

    public int getNumMoviesSeen() {
        return numMoviesSeen;
    }

    public void setNumMoviesSeen(int numMoviesSeen) {
        this.numMoviesSeen = numMoviesSeen;
    }

    public int getNumMoviesFav() {
        return numMoviesFav;
    }

    public void setNumMoviesFav(int numMoviesFav) {
        this.numMoviesFav = numMoviesFav;
    }

    public int getNumFriends() {
        return numFriends;
    }

    public void setNumFriends(int numFriends) {
        this.numFriends = numFriends;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}