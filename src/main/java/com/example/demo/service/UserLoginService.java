package com.example.demo.service;

import com.example.demo.entity.User;

/**
 * Created by the_s on 9/8/2017.
 */
public interface UserLoginService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}
