package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import com.example.demo.service.UserLoginService;
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
    public ResponseEntity<Boolean> login(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userServiceEmail.findUserByEmail(auth.getName());
        System.out.println(user.getName());
        if (user != null){
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        }else{
            return new ResponseEntity<Boolean>(false,HttpStatus.CONFLICT);
        }
    }

    @PostMapping("registration")
    public ResponseEntity<Void> createNewUser(@RequestBody User user, UriComponentsBuilder builder){
        User userExists = userServiceEmail.findUserByEmail(user.getEmail());

        if(userExists != null){
           return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        userServiceEmail.saveUser(user);
        headers.setLocation(builder.path("/registration/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
    }
}
