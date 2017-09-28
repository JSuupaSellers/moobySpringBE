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

import javax.xml.ws.Response;
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

    @GetMapping("user/single/{username}/")
    public ResponseEntity<User> getUserByUserName(@PathVariable("username") String username){
        User user = userService.getUserByUserName(username);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    @GetMapping("user/email/{email}/")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email")String email){
        User user = userService.findUserByEmail(email);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> list = userService.getAllUsers();
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }

    @GetMapping("user")
    @ResponseBody
    public String addUser(@RequestBody User user){
        boolean flag = userService.addUser(user);
        if(!flag){
            return Boolean.FALSE.toString();
        }
        return Boolean.TRUE.toString();
    }
    @PutMapping("user")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        userService.updateUser(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
