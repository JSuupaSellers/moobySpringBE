package com.example.demo.dao;

import com.example.demo.entity.MoviesSeen;
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
public class MoviesSeenDAO implements IMoviesSeenDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<MoviesSeen> getAllMoviesSeen() {
        String hql = "FROM MoviesSeen as ms ORDER BY ms.movieID";
        return (List<MoviesSeen>)entityManager.createQuery(hql)
                .getResultList();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<MoviesSeen> getMoviesSeenFor(int userId) {
        String hql = "FROM MoviesSeen as ms WHERE ms.userId = ?";
        return (List<MoviesSeen>)entityManager.createQuery(hql).setParameter(1,userId);
    }

    @Override
    public void addMovieToSeen(MoviesSeen movie) {
        entityManager.persist(movie);
    }

    @Override
    public void updateMovieSeen(MoviesSeen movie) {
        MoviesSeen req = entityManager.find(MoviesSeen.class,movie.getMovieID());
        req.setFaved(movie.isFaved());
        req.setLiked(movie.isLiked());
        entityManager.flush();
    }

    private MoviesSeen getMovie(int movieId){
        String hql = "FROM MoviesSeen as ms WHERE ms.movieID = ?";
        return (MoviesSeen) entityManager.createQuery(hql).setParameter(1, movieId).getResultList().get(0);
    }

    @Override
    public void deleteFromSeenList(int movieId) {
        entityManager.remove(getMovie(movieId));
    }

    @Override
    public boolean movieListContainsMovie(int movieId) {
        String hql = "FROM MoviesSeen as ms WHERE ms.movieID = ?";
        int count = entityManager.createQuery(hql).setParameter(1,movieId)
                .getResultList().size();
        return count > 0;
    }
}
