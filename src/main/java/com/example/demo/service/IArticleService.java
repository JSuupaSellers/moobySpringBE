package com.example.demo.service;

import com.example.demo.entity.Article;

import java.util.List;

/**
 * Created by Joshua on 9/3/2017.
 */
public interface IArticleService {
    List<Article> getAllArticles();
    Article getArticleById(int articleId);
    boolean addArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(int articleId);
}
