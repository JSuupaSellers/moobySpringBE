package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by the_s on 9/5/2017.
 */
@Entity
@Table(name = "friends")
public class Friend implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "friend_id")
    private int friendId;
    @Column(name = "user1")
    private int userOne;
    @Column(name = "user2")
    private int userTwo;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public int getUserOne() {
        return userOne;
    }

    public void setUserOne(int userOne) {
        this.userOne = userOne;
    }

    public int getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(int userTwo) {
        this.userTwo = userTwo;
    }
}
