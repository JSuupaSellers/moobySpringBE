package com.example.demo.dao;

import com.example.demo.entity.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by the_s on 9/7/2017.
 */
@Transactional
@Repository
public class RoleDAO implements IRoleDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Role findByRole(String role) {
        return entityManager.find(Role.class, role);
    }
}
