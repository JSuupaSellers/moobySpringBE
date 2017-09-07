package com.example.demo.dao;

import com.example.demo.entity.FriendRequest;
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
public class FriendRequestDAO implements IFriendRequestDAO {
    @PersistenceContext
    private EntityManager entityManager;
    @SuppressWarnings("unchecked")
    @Override
    public List<FriendRequest> getAllRequests(){
        String hql = "FROM FriendRequest as atcl ORDER BY atcl.friendRequestId";
        return (List<FriendRequest>)entityManager.createQuery(hql)
                .getResultList();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<FriendRequest> getFriendRequestsToUserId(int userId) {
        String hql = "FROM FriendRequest as atcl WHERE atcl.userId = ?";
        return entityManager.createQuery(hql).setParameter(1, userId)
                .getResultList();
    }

    @Override
    public FriendRequest getSingleFriendRequestByUserId(int userId, int fromUserId) {
        String hql = "FROM FriendRequest as atcl WHERE atcl.userId = ? and atcl.fromUser = ?";
        return (FriendRequest)entityManager.createQuery(hql).setParameter(1, userId).setParameter(2,fromUserId)
                .getResultList().get(0);
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<FriendRequest> getFriendRequestsByUserId(int fromUserId) {
        String hql = "FROM FriendRequest as atcl WHERE atcl.fromUser = ?";
        return entityManager.createQuery(hql).setParameter(1,fromUserId)
                .getResultList();
    }

    @Override
    public void addFriendRequest(FriendRequest request) {
        entityManager.persist(request);
    }

    @Override
    public void updateFriendRequest(FriendRequest request) {
        FriendRequest req = getSingleFriendRequestByUserId(request.getUserId(), request.getFromUser());
        req.setStatus(request.getStatus());
        entityManager.flush();
    }

    @Override
    public void deleteFriendRequest(int userId, int fromUserId) {
        entityManager.remove(getSingleFriendRequestByUserId(userId, fromUserId));
    }

    @Override
    public boolean friendRequestExists(int userId, int fromUserId) {
        String hql = "FROM FriendRequest as atcl WHERE atcl.userId = ? and atcl.fromUser = ?";
        int count = entityManager.createQuery(hql).setParameter(1,userId).setParameter(2, fromUserId)
                .getResultList().size();
        return count > 0;
    }
}
