package com.example.demo.controller;

import com.example.demo.entity.Friend;
import com.example.demo.entity.FriendRequest;
import com.example.demo.entity.FriendRequestResponse;
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
 * Created by the_s on 9/5/2017.
 */
@Controller
@RequestMapping("data")
public class FriendRequestController {
    @Autowired
    private IFriendRequestService friendRequestService;
    @Autowired
    private IUserService userService;
    @Autowired private IFriendService friendService;

    @GetMapping("friendRequests")
    public ResponseEntity<List<FriendRequest>> getAllFriendRequests(){
        List<FriendRequest> list = friendRequestService.getAllFriendRequests();
        return new ResponseEntity<List<FriendRequest>>(list, HttpStatus.OK);
    }

    @GetMapping("friendRequests/{id}")
    public ResponseEntity<List<FriendRequestResponse>> getFriendRequestsFor(@PathVariable("id") Integer id){
        List<FriendRequest> list = friendRequestService.getAllFriendRequestsForUser(id);
        List<FriendRequestResponse> resList = new ArrayList<>();

        for(FriendRequest request : list){
            FriendRequestResponse res = new FriendRequestResponse();
            res.setTimeSent(request.getDateSent());
            res.setToUser(request.getUserId());
            res.setFromUser(userService.getUserById(request.getFromUser()).getUsername());
            res.setFromUserId(request.getFromUser());
            resList.add(res);
        }
        return new ResponseEntity<>(resList, HttpStatus.OK);
    }

    @PostMapping("friendRequest")
    public ResponseEntity<FriendRequestResponse> addFriendRequest(@RequestBody FriendRequest request, UriComponentsBuilder builder){
        request.setStatus("sent");
        request.setDateSent(System.currentTimeMillis());
        FriendRequestResponse response = new FriendRequestResponse();
        for(Friend friend : friendService.getAllFriendsForUser(request.getFromUser())){
            if(friend.getUserOne() == request.getUserId() || friend.getUserTwo() == request.getUserId()){
                response.setMessage("Already Friends!");
                return new ResponseEntity<FriendRequestResponse>(response,HttpStatus.OK);
            }
        }
        boolean flag = friendRequestService.sendFriendRequest(request);
        if(!flag){
            return new ResponseEntity<>(response,HttpStatus.CONFLICT);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("friendRequest/{id}").buildAndExpand(request.getUserId()).toUri());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("friendRequest")
    public ResponseEntity<FriendRequest> updateFriendRequest(@RequestBody FriendRequest request){
        friendRequestService.updateFriendRequest(request);
        return new ResponseEntity<FriendRequest>(request,HttpStatus.OK);
    }

    @DeleteMapping("friendRequest/{id}/{from}")
    public ResponseEntity<Boolean> deleteFriendRequest(@PathVariable("id") Integer id, @PathVariable("from") Integer from){
        boolean flag = friendRequestService.deleteFriendRequest(id,from);
        if(!flag){
            return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }


}
