package com.example.demo.controller;

import com.example.demo.config.security.TokenAuthenticationService;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import com.example.demo.service.UserLoginService;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 * Created by the_s on 9/7/2017.
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    IUserService userService;
    @Autowired
    UserLoginService userServiceEmail;

    @GetMapping("login")
    public ResponseEntity<String> login(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userServiceEmail.findUserByEmail(auth.getName());
        System.out.println(user.getName());
        System.out.println(TokenAuthenticationService.JWT + " Login");
        return new ResponseEntity<String>(TokenAuthenticationService.JWT,HttpStatus.OK);
    }

    @PostMapping("registration")
    public ResponseEntity<Void> createNewUser(@RequestBody User user){
        User userExists = userServiceEmail.findUserByEmail(user.getEmail());

        if(userExists != null){
           return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        System.out.println("registration!! " + user.getUsername() + " name: " + user.getName() + " email : " + user.getEmail());
        userServiceEmail.saveUser(user);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
