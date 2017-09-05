package com.example.demo.client;

import com.example.demo.entity.Article;
import com.example.demo.entity.FriendRequest;
import com.example.demo.entity.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by Joshua on 9/3/2017.
 */
public class RestClientUtil {
    public void getArticleByIdDemo(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://locolhost:8080/user/article/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Article> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Article.class, 1);
        Article article = responseEntity.getBody();
        System.out.println("Id:"+article.getArticleId()+", Title:"+article.getTitle()
                +", Category:"+article.getCategory());
    }
    public void getAllArticlesDemo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/articles";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Article[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Article[].class);
        Article[] articles = responseEntity.getBody();
        for(Article article : articles) {
            System.out.println("Id:"+article.getArticleId()+", Title:"+article.getTitle()
                    +", Category: "+article.getCategory());
        }
    }
    public void getAllUsersDemo(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/data/users";
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<User[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, User[].class);
        User[] users = responseEntity.getBody();
        for(User user : users){
            System.out.println("Id: " + user.getUserId() + " email: " + user.getEmail() + " username: " + user.getUserName());
        }
    }

    public void addUser(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/data/user";
        User user = new User();
        user.setEmail("Test@email.com");
        user.setPassword("123445");
        user.setUserName("New Test User");
        HttpEntity<User> requestEntity = new HttpEntity<>(user,headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());
    }

    public void getAllRequestsDemo(){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/data/friendRequests";
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<FriendRequest[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, FriendRequest[].class);
        FriendRequest[] friendRequests = responseEntity.getBody();
        for(FriendRequest request : friendRequests){
            System.out.println("userId: "  + request.getUserId() + " from: " +
                    request.getFromUser() + " time in millis " +
                    request.getDateSent() + " status: " + request.getStatus());
        }
    }
    public void sendFriendRequestDemo(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/data/friendRequest";
        FriendRequest obj = new FriendRequest();
        obj.setStatus("accepted");
        obj.setDateSent(System.currentTimeMillis());
        obj.setFromUser(1);
        obj.setUserId(2);
        HttpEntity<FriendRequest> requestEntity = new HttpEntity<>(obj, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());
    }
    public void addArticleDemo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/article";
        Article objArticle = new Article();
        objArticle.setTitle("Spring REST Security using Hibernate");
        objArticle.setCategory("Spring");
        HttpEntity<Article> requestEntity = new HttpEntity<Article>(objArticle, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());
    }
    public void getFriendRequestsFromId(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/data/friendRequests/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<FriendRequest[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, FriendRequest[].class,1);
        FriendRequest[] friendRequests = responseEntity.getBody();
        for(FriendRequest request : friendRequests){
            System.out.println("userId: "  + request.getUserId() + " from: " +
                    request.getFromUser() + " time in millis " +
                    request.getDateSent() + " status: " + request.getStatus());
        }
    }
    public void deleteRequestDemo(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/friendRequest/{id}";
        HttpEntity<FriendRequest> requestEntity = new HttpEntity<FriendRequest>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class,4);
    }


    public void deleteArticleDemo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/article/{id}";
        HttpEntity<Article> requestEntity = new HttpEntity<Article>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class,4);
    }

    public static void main(String args[]){
        RestClientUtil util = new RestClientUtil();
        util.getFriendRequestsFromId();
//        util.getAllRequestsDemo();
//        util.addArticleDemo();
//        util.getAllArticlesDemo();
//        util.sendFriendRequestDemo();
//        util.addUser();
    }
}
