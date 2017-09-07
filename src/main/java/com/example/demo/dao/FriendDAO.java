package com.example.demo.dao;

import com.example.demo.entity.Friend;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by the_s on 9/5/2017.
 */
@Transactional
@Repository
public class FriendDAO implements IFriendDAO{
    @PersistenceContext
    private EntityManager entityManager;
    @SuppressWarnings("unchecked")
    @Override
    public List<Friend> getAllFriends() {
        String hql = "FROM Friend as fr ORDER BY fr.friendId";
        return (List<Friend>)entityManager.createQuery(hql)
                .getResultList();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Friend> getFriendsForUser(int userId) {
        String hql = "From Friend as fr WHERE fr.userOne = ?";
        return entityManager.createQuery(hql).setParameter(1, userId)
                .getResultList();
    }

    @Override
    public Friend getSingleFriendPair(int user1, int user2) {
        String hql = "FROM Friend as fr WHERE fr.userOne = ? and fr.userTwo = ?";
        return (Friend)entityManager.createQuery(hql).setParameter(1, user1).setParameter(2, user2)
                .getResultList().get(0);
    }

    @Override
    public void addFriend(Friend friend) {
        entityManager.persist(friend);
    }

    @Override
    public void deleteFriend(int user1,int user2) {
        entityManager.remove(getSingleFriendPair(user1,user2));
    }

    @Override
    public boolean friendExists(int user1, int user2) {
        String hql = "FROM Friend as fr WHERE fr.userOne = ? and fr.userTwo = ?";
        int count = entityManager.createQuery(hql).setParameter(1,user1).setParameter(2,user2)
                .getResultList().size();
        return count > 0;
    }
}
