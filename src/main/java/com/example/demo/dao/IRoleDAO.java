package com.example.demo.dao;

import com.example.demo.entity.Role;

/**
 * Created by the_s on 9/7/2017.
 */
public interface IRoleDAO {
    Role findByRole(String role);
}
