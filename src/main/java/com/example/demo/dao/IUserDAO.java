package com.example.demo.dao;

import com.example.demo.entity.User;

import java.util.List;

/**
 * Created by Joshua on 9/4/2017.
 */
public interface IUserDAO {
    List<User> getAllUsers();
    User getUserById(int userId);
    User getUserByUsername(String username);
    void addUser(User user);
    void updateUser(User user);
    boolean userExists(String username);
}
