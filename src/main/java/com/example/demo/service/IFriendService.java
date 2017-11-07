package com.example.demo.service;

import com.example.demo.entity.Friend;

import java.util.List;

/**
 * Created by the_s on 9/5/2017.
 */

public interface IFriendService {
    List<Friend> getAllFriends();
    List<Friend> getAllFriendsForUser(int userId);
    boolean addFriend(Friend friend);
    boolean deleteFriend(int friendId);
}
