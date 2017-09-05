package com.example.demo.dao;

import com.example.demo.entity.FriendRequest;

import java.util.List;

/**
 * Created by the_s on 9/5/2017.
 */
public interface IFriendRequestDAO {
    List<FriendRequest> getAllRequests();
    List<FriendRequest> getFriendRequestsToUserId(int userId);
    List<FriendRequest> getFriendRequestsByUserId(int fromUserId);
    FriendRequest getSingleFriendRequestByUserId(int userId, int fromUserId);
    void addFriendRequest(FriendRequest request);
    void updateFriendRequest(FriendRequest request);
    void deleteFriendRequest(int userId, int fromUserId);
    boolean friendRequestExists(int userId, int fromUserId);
}
