package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

/**
 * Created by Joshua on 9/4/2017.
 */
public interface IUserService {
    List<User> getAllUsers();
    User getUserById(int userId);
    User getUserByUserName(String username);
    boolean addUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
    User findUserByEmail(String email);
    void saveUser(User user);
}
