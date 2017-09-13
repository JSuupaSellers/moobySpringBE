package com.example.demo.service;

import com.example.demo.dao.IRoleDAO;
import com.example.demo.dao.IUserDAO;
import com.example.demo.dao.RoleRepo;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Joshua on 9/4/2017.
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private IRoleDAO roleDAO;
    @Autowired
    private RoleRepo roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
        if(userDAO.userExists(user.getUsername())){
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

    @Override
    public User findUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userDAO.save(user);
    }
}
