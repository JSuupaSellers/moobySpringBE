package com.example.demo.dao;

import com.example.demo.entity.Friend;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
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
        String hql = "From Friend as fr WHERE fr.userOne = ? or fr.userTwo = ?";
        return entityManager.createQuery(hql).setParameter(1, userId).setParameter(2,userId)
                .getResultList();
    }
    @SuppressWarnings("unchecked")
    @Override
    public Friend getSingleFriendPair(int user1, int user2) {
        String hql = "FROM Friend as fr WHERE fr.userOne = ? and fr.userTwo = ?";
        List<Friend> list = (List<Friend>)entityManager.createQuery(hql).setParameter(1,user1).setParameter(2,user2).getResultList();
        if(list.size() > 0){
            return list.get(0);
        }else{
            return (Friend)entityManager.createQuery(hql).setParameter(1,user2).setParameter(2,user1).getSingleResult();
        }
    }

    private Friend getFriendByFriendId(int id){
        String hql = "FROM Friend WHERE friendId = :friendId";
        return (Friend)entityManager.createQuery(hql).setParameter("friendId", id).getSingleResult();
    }

    @Override
    public void addFriend(Friend friend) {
        entityManager.persist(friend);
    }

    @Override
    public void deleteFriend(int friendId) {
        entityManager.remove(getFriendByFriendId(friendId));
    }

    @Override
    public boolean friendExists(int friendId) {
        String hql = "FROM Friend as fr WHERE fr.friendId = ?";
        int count = entityManager.createQuery(hql).setParameter(1,friendId)
                .getResultList().size();
        return count > 0;
    }
}
