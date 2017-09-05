package com.example.demo.controller;

import com.example.demo.entity.FriendRequest;
import com.example.demo.service.IFriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by the_s on 9/5/2017.
 */
@Controller
@RequestMapping("data")
public class FriendRequestController {
    @Autowired
    private IFriendRequestService friendRequestService;

    @GetMapping("friendRequests")
    public ResponseEntity<List<FriendRequest>> getAllFriendRequests(){
        List<FriendRequest> list = friendRequestService.getAllFriendRequests();
        return new ResponseEntity<List<FriendRequest>>(list, HttpStatus.OK);
    }

    @GetMapping("friendRequests/{id}")
    public ResponseEntity<List<FriendRequest>> getFriendRequestsFor(@PathVariable("id") Integer id){
        List<FriendRequest> list = friendRequestService.getAllFriendRequestsForUser(id);
        return new ResponseEntity<List<FriendRequest>>(list, HttpStatus.OK);
    }

    @PostMapping("friendRequest")
    public ResponseEntity<Void> addFriendRequest(@RequestBody FriendRequest request, UriComponentsBuilder builder){
        boolean flag = friendRequestService.sendFriendRequest(request);
        if(!flag){
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("friendRequest/{id}").buildAndExpand(request.getUserId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("friendRequest")
    public ResponseEntity<FriendRequest> updateFriendRequest(@RequestBody FriendRequest request){
        friendRequestService.updateFriendRequest(request);
        return new ResponseEntity<FriendRequest>(request,HttpStatus.OK);
    }

    @DeleteMapping("friendRequest/{id}/{from}")
    public ResponseEntity<Void> deleteFriendRequest(@PathVariable("id") Integer id, @PathVariable Integer from){
        friendRequestService.deleteFriendRequest(id,from);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


}
