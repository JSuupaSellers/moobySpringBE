package com.example.demo.controller;

import com.example.demo.entity.Friend;
import com.example.demo.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by the_s on 9/6/2017.
 */
@Controller
@RequestMapping("data")
public class FriendController {
    @Autowired
    private IFriendService friendService;

    @GetMapping("friends")
    public ResponseEntity<List<Friend>> getAllFriends(){
        List<Friend> list = friendService.getAllFriends();
        return new ResponseEntity<List<Friend>>(list, HttpStatus.OK);
    }
    @GetMapping("friends/{id}")
    public ResponseEntity<List<Friend>> getAllFriendsForUser(@PathVariable("id")Integer id){
        List<Friend> list = friendService.getAllFriendsForUser(id);
        return new ResponseEntity<List<Friend>>(list,HttpStatus.OK);
    }
    @PostMapping("friend")
    public ResponseEntity<Void> addFriendPair(@RequestBody Friend friend, UriComponentsBuilder builder){
        boolean flag = friendService.addFriend(friend);
        if(!flag){
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/friend/{id}").buildAndExpand(friend.getUserOne()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    @DeleteMapping("friend")
    public ResponseEntity<Void> deleteFriendPair(@RequestBody Friend friend, UriComponentsBuilder builder){
        boolean flag = friendService.deleteFriend(friend);
        if(!flag){
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/friend/{id}").buildAndExpand(friend.getFriendId()).toUri());
        return new ResponseEntity<Void>(headers,HttpStatus.GONE);
    }
}
