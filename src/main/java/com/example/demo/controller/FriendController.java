package com.example.demo.controller;

import com.example.demo.entity.Friend;
import com.example.demo.entity.FriendResponse;
import com.example.demo.entity.FriendUpdatedResponse;
import com.example.demo.service.IFriendRequestService;
import com.example.demo.service.IFriendService;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by the_s on 9/6/2017.
 */
@Controller
@RequestMapping("data")
public class FriendController {
    @Autowired
    private IFriendService friendService;
    @Autowired private IUserService userService;
    @Autowired private IFriendRequestService requestService;
    @GetMapping("friends")
    public ResponseEntity<List<Friend>> getAllFriends(){
        List<Friend> list = friendService.getAllFriends();
        return new ResponseEntity<List<Friend>>(list, HttpStatus.OK);
    }
    @GetMapping("friends/{id}")
    public ResponseEntity<List<FriendResponse>> getAllFriendsForUser(@PathVariable("id")Integer id){
        List<Friend> list = friendService.getAllFriendsForUser(id);
        List<FriendResponse> resList = new ArrayList<>();
        for(Friend friend : list){
            FriendResponse res = new FriendResponse();
            if(friend.getUserOne() != id){
                res.setId(friend.getFriendId());
                res.setFriendId(friend.getUserOne());
                res.setFriendUserName(userService.getUserById(friend.getUserOne()).getUsername());
            }else if(friend.getUserOne() == id){
                res.setId(friend.getFriendId());
                res.setFriendId(friend.getUserTwo());
                res.setFriendUserName(userService.getUserById(friend.getUserTwo()).getUsername());
            }
            resList.add(res);
        }
        return new ResponseEntity<>(resList,HttpStatus.OK);
    }
    @PostMapping("friend")
    public ResponseEntity<FriendUpdatedResponse> addFriendPair(@RequestBody Friend friend){
        boolean flag = friendService.addFriend(friend);
        FriendUpdatedResponse res = new FriendUpdatedResponse();
        res.setAdded(false);
        if(!flag){
            return new ResponseEntity<>(res,HttpStatus.CONFLICT);
        }
        res.setAdded(true);
        requestService.deleteFriendRequest(friend.getUserOne(),friend.getUserTwo());
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    @DeleteMapping("friend/{id}")
    public ResponseEntity<Boolean> deleteFriendPair(@PathVariable("id")int id){
        boolean flag = friendService.deleteFriend(id);
        FriendUpdatedResponse res = new FriendUpdatedResponse();

        if(!flag){
            res.setRemoved(false);
            return new ResponseEntity<>(false,HttpStatus.CONFLICT);
        }
        res.setRemoved(true);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }
}
