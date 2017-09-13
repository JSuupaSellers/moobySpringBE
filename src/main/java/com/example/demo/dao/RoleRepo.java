package com.example.demo.dao;

import com.example.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by the_s on 9/8/2017.
 */
@Repository("roleRepository")
public interface RoleRepo extends JpaRepository<Role,Long>{
    Role findByRole(String role);
}
