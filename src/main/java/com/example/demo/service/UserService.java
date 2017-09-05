package com.example.demo.service;

import com.example.demo.dao.IUserDAO;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Joshua on 9/4/2017.
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDAO userDAO;

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(int userId) {
        User obj = userDAO.getUserById(userId);
        return obj;
    }

    @Override
    public User getUserByUserName(String username) {
        User obj = userDAO.getUserByUsername(username);
        return obj;
    }

    @Override
    public boolean addUser(User user) {
        if(userDAO.userExists(user.getUserName())){
            return false;
        }else{
            userDAO.addUser(user);
            return true;
        }
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);

    }

    @Override
    public void deleteUser(int userId) {

    }
}
