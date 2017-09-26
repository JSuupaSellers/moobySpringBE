package com.example.demo.service;

import com.example.demo.dao.RoleRepo;
import com.example.demo.dao.UserRepo;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by the_s on 9/8/2017.
 */
@Service("userLoginService")
public class UserServiceImpl implements UserLoginService{

    @Autowired
    private UserRepo userRepository;
    @Autowired
    private RoleRepo roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println("PASSWORD!!: " + user.getPassword());
        System.out.println("REGISTRATION:: " + user.getUsername());
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

}