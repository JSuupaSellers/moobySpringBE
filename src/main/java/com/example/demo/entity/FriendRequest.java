package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by the_s on 9/5/2017.
 */
@Entity
@Table(name = "friend_requests")
public class FriendRequest implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int friendRequestId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "from_user")
    private int fromUser;
    @Column(name = "status")
    private String status;
    @Column(name = "date_sent")
    private long dateSent;

    public long getDateSent() {
        return dateSent;
    }

    public int getFriendRequestId() {
        return friendRequestId;
    }

    public void setFriendRequestId(int friendRequestId) {
        this.friendRequestId = friendRequestId;
    }

    public void setDateSent(long dateSent) {
        this.dateSent = dateSent;
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

    public int getFromUser() {
        return fromUser;
    }

    public void setFromUser(int fromUser) {
        this.fromUser = fromUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
