package com.example.demo.dao;

import com.example.demo.entity.MovieRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by the_s on 9/11/2017.
 */
@Transactional
@Repository
public class MovieRequestDAO implements IMovieRequestDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<MovieRequest> getAllMovieRequests() {
        String hql = "FROM MovieRequest as mr ORDER BY mr.movieRequestId";
        return (List<MovieRequest>)entityManager.createQuery(hql)
                .getResultList();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<MovieRequest> getMovieRequestsForUserId(int userId) {
        String hql = "FROM MovieRequest as mr WHERE mr.userId = ?";
        return entityManager.createQuery(hql).setParameter(1, userId).getResultList();
    }

    @Override
    public MovieRequest getSingleMovieRequest(int userId, int fromUserId) {
        String hql = "FROM MovieRequest as mr WHERE mr.userId = ? and mr.fromUserId = ?";
        return (MovieRequest)entityManager.createQuery(hql).setParameter(1,userId).setParameter(2,fromUserId)
                .getResultList().get(0);
    }

    private MovieRequest getMovieFromId(int movieRequestId){
        String hql = "FROM MovieRequest as mr WHERE mr.movieRequestId = ?";
        return (MovieRequest) entityManager.createQuery(hql).setParameter(1,movieRequestId).getSingleResult();
    }

    @Override
    public void addMovieRequest(MovieRequest request) {
        entityManager.persist(request);
    }

    @Override
    public boolean movieRequestExists(int movieRequestId) {
        String hql = "FROM MovieRequest as mr WHERE mr.movieRequestId = ?";
        int count = entityManager.createQuery(hql).setParameter(1,movieRequestId)
                .getResultList().size();
        return count > 0;
    }

    @Override
    public void removeMovieRequest(int movieRequestID) {
        entityManager.remove(getMovieFromId(movieRequestID));
    }
}
