package com.example.demo.service;

import com.example.demo.dao.IFriendRequestDAO;
import com.example.demo.entity.FriendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by the_s on 9/5/2017.
 */
@Service
public class FriendRequestService implements IFriendRequestService {
    @Autowired
    private IFriendRequestDAO friendRequestDAO;

    @Override
    public List<FriendRequest> getAllFriendRequests() {
        return friendRequestDAO.getAllRequests();
    }

    @Override
    public List<FriendRequest> getAllFriendRequestsForUser(int userId) {
        return friendRequestDAO.getFriendRequestsToUserId(userId);
    }

    @Override
    public FriendRequest getSingleFriendRequestForUser(int userId, int fromUserId) {
        return friendRequestDAO.getSingleFriendRequestByUserId(userId, fromUserId);
    }

    @Override
    public boolean sendFriendRequest(FriendRequest request) {
        if(friendRequestDAO.friendRequestExists(request.getUserId(),request.getFromUser())){
            return false;
        }else{
            friendRequestDAO.addFriendRequest(request);
            return true;
        }
    }

    @Override
    public void updateFriendRequest(FriendRequest request) {
        friendRequestDAO.updateFriendRequest(request);
    }

    @Override
    public void deleteFriendRequest(int userId, int fromUserId) {
        friendRequestDAO.deleteFriendRequest(userId,fromUserId);
    }
}
