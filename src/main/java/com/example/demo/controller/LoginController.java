package com.example.demo.controller;

import com.example.demo.config.security.TokenAuthenticationService;
import com.example.demo.entity.LoginResponse;
import com.example.demo.entity.RegistrationResponse;
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
    public ResponseEntity<Void> login(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("LOGIN::: " + auth.getName());
        User user = userService.findUserByEmail(auth.getName());
        LoginResponse response = new LoginResponse();
        if(user != null){
            response.setResponse("Login Succesesful");
            response.setLoginStatus(true);
            response.setToken(TokenAuthenticationService.JWT);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        response.setResponse("Login Failed. Make sure email and password is correct.");
        response.setLoginStatus(false);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("registration")
    public ResponseEntity<RegistrationResponse> createNewUser(@RequestBody User user){
        User userExists = userServiceEmail.findUserByEmail(user.getEmail());
        User usernameExists = userService.getUserByUserName(user.getUsername());
        RegistrationResponse res= new RegistrationResponse();
        if(userExists != null){
            res.setResponse("Email already in use.");
           return new ResponseEntity<>(res,HttpStatus.OK);
        }
        if(usernameExists != null){
            res.setResponse("Username already in use.");
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        res.setResponse("Registration Successful!");
        HttpHeaders headers = new HttpHeaders();
        System.out.println("registration!! " + user.getUsername() + " name: " + user.getName() + " email : " + user.getEmail());
        userServiceEmail.saveUser(user);
        return new ResponseEntity<>(res,HttpStatus.CREATED);
    }
}
