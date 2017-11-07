package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Joshua on 9/4/2017.
 */
@Transactional
@Repository
public class UserDAO implements IUserDAO{
    @PersistenceContext
    private EntityManager entityManager;
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers(){
        String hql = "FROM User as usr ORDER BY usr.id";
        return (List<User>)entityManager.createQuery(hql).getResultList();
    }

    @Override
    public User getUserById(int userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    public User getUserByUsername(String username) {
        String hql = "from User where username = :username";
        try{
            return (User)entityManager.createQuery(hql).setParameter("username",username).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsersByUserNamePartial(String query){
        String hql = "from User where username like :username";
        return (List<User>)entityManager.createQuery(hql).setParameter("username",query + "%").getResultList();

    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        User usr = getUserById(user.getId());
        usr.setEmail(user.getEmail());
        entityManager.flush();
    }

    @Override
    public boolean userExists(String username) {
        String hql = "FROM User as usr WHERE usr.name = ?";
        int count = entityManager.createQuery(hql).setParameter(1,username).getResultList().size();
        return count > 0;
    }

    @Override
    public User findByEmail(String email) {
        String hql = "from User where email = :email";
        return (User)entityManager.createQuery(hql).setParameter("email", email).getSingleResult();
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }
}
