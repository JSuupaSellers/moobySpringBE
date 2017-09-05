package com.example.demo.service;

import com.example.demo.entity.FriendRequest;

import java.util.List;

/**
 * Created by the_s on 9/5/2017.
 */
public interface IFriendRequestService {
    List<FriendRequest> getAllFriendRequests();
    List<FriendRequest> getAllFriendRequestsForUser(int userId);
    FriendRequest getSingleFriendRequestForUser(int userId, int fromUserId);
    boolean sendFriendRequest(FriendRequest request);
    void updateFriendRequest(FriendRequest request);
    void deleteFriendRequest(int userId, int fromUserId);
}
