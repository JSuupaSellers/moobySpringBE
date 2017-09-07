package com.example.demo.service;

import com.example.demo.dao.IFriendDAO;
import com.example.demo.entity.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by the_s on 9/5/2017.
 */
@Service
public class FriendService implements IFriendService {
    @Autowired
    private IFriendDAO friendDAO;

    @Override
    public List<Friend> getAllFriends() {
        return friendDAO.getAllFriends();
    }

    @Override
    public List<Friend> getAllFriendsForUser(int userId) {
        return friendDAO.getFriendsForUser(userId);
    }

    @Override
    public boolean addFriend(Friend friend) {
        if(friendDAO.friendExists(friend.getUserOne(),friend.getUserTwo())){
            return false;
        }else{
            friendDAO.addFriend(friend);
            return true;
        }
    }

    @Override
    public boolean deleteFriend(Friend friend) {
        if(!friendDAO.friendExists(friend.getUserOne(),friend.getUserTwo())){
            return false;
        }else{
            friendDAO.deleteFriend(friend.getUserOne(), friend.getUserTwo());
            return true;
        }
    }
}
