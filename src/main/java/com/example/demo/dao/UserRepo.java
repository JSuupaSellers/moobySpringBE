package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by the_s on 9/8/2017.
 */
@Repository("userRepository")
public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
