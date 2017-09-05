package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Joshua on 9/4/2017.
 */
@Controller
@RequestMapping("data")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id){
        User user = userService.getUserById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("user/{username}")
    public ResponseEntity<User> getUserByUserName(@PathVariable("username") String username){
        User user = userService.getUserByUserName(username);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> list = userService.getAllUsers();
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }

    @PostMapping("user")
    public ResponseEntity<Void> addUser(@RequestBody User user, UriComponentsBuilder builder){
        boolean flag = userService.addUser(user);
        if(!flag){
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/user/{id}").buildAndExpand(user.getUserId()).toUri());
        return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
    }
    @PutMapping("user")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        userService.updateUser(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
