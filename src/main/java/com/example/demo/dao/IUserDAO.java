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
    List<User> getUsersByUserNamePartial(String query);
    void addUser(User user);
    void updateUser(User user);
    boolean userExists(String username);
    User findByEmail(String email);
    void save(User user);
}
