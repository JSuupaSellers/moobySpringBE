package com.example.demo.dao;

import com.example.demo.entity.Friend;

import java.util.List;

/**
 * Created by the_s on 9/5/2017.
 */
public interface IFriendDAO {
    List<Friend> getAllFriends();
    List<Friend> getFriendsForUser(int userId);
    Friend getSingleFriendPair(int user1, int user2);
    void addFriend(Friend friend);
    void deleteFriend(int friendId);
    boolean friendExists(int friendId);
}
