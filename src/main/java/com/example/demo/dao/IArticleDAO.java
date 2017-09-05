package com.example.demo.dao;

import com.example.demo.entity.Article;

import java.util.List;

/**
 * Created by Joshua on 9/3/2017.
 */
public interface IArticleDAO {
    List<Article> getAllArticles();
    Article getArticleById(int articleId);
    void addArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(int articleId);
    boolean articleExists(String title, String category);
}
