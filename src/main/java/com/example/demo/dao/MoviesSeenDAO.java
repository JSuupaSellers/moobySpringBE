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
        String hql = "FROM MoviesSeen as ms ORDER BY ms.userId";
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
    public boolean updateMovieSeen(MoviesSeen movie) {
        String hql = "UPDATE MoviesSeen as ms SET ms.liked = ?, ms.faved = ? WHERE ms.userId = ? and ms.movieId = ?";
        entityManager.createQuery(hql).setParameter(1,movie.isLiked()).setParameter(2,movie.isFaved()).setParameter(3,movie.getUserId())
                .setParameter(4,movie.getMovieId());
//        MoviesSeen req = entityManager.find(MoviesSeen.class,movie.getMovieID());
//        req.setFaved(movie.isFaved());
//        req.setLiked(movie.isLiked());
//        entityManager.flush();
        return true;
    }

    private MoviesSeen getMovie(MoviesSeen moviesSeen){
        String hql = "FROM MoviesSeen as ms WHERE ms.movieId = ? and ms.userId = ?";
        return (MoviesSeen) entityManager.createQuery(hql).setParameter(1, moviesSeen.getMovieId()).setParameter(2,moviesSeen.getUserId()).getResultList().get(0);
    }

    @Override
    public void deleteFromSeenList(MoviesSeen moviesSeen) {
//        String hql = "delete from MoviesSeen where movieId = :movieId AND userId = :userId";
//        entityManager.createQuery(hql).setParameter("movieId", moviesSeen.getMovieId()).setParameter("userId", moviesSeen.getUserId());
        entityManager.remove(getMovie(moviesSeen));
    }

    @Override
    public boolean movieListContainsMovie(MoviesSeen movie) {
        String hql = "FROM MoviesSeen as ms WHERE ms.userId = ? and ms.movieId = ?";
        int count = entityManager.createQuery(hql).setParameter(1,movie.getUserId()).setParameter(2,movie.getMovieId())
                .getResultList().size();
        return count > 0;
    }

    @Override
    public MoviesSeen getMovieForUserWithId(int movieId, int userId) {
        String hql = "from MoviesSeen where movieId = :movieId and userId = :userId";
        return (MoviesSeen)entityManager.createQuery(hql).setParameter("movieId",movieId).setParameter("userId",userId).getSingleResult();
    }
    @Override
    public void delete(MoviesSeen moviesSeen){
        entityManager.remove(getMovieForUserWithId(moviesSeen.getMovieId(),moviesSeen.getUserId()));
    }
}
