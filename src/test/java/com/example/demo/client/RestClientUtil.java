package com.example.demo.client;

import com.example.demo.entity.*;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import sun.net.www.protocol.http.AuthenticationHeader;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;

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
            System.out.println("Id: " + " email: " + user.getEmail() + " username: ");
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
    public void addFriendPair(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/data/friend";
        Friend obj = new Friend();
        obj.setUserOne(3);
        obj.setUserTwo(5);
        HttpEntity<Friend> requestEntity = new HttpEntity<>(obj, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());
    }
    public void addMovieRequest(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/data/movieRequest";
        MovieRequest movieRequest = new MovieRequest();
        movieRequest.setFromUser(3);
        movieRequest.setMovieId(2314);
        movieRequest.setTimeSent(BigInteger.valueOf(System.currentTimeMillis()));
        movieRequest.setUserId(1);
        HttpEntity<MovieRequest> requestEntity = new HttpEntity<MovieRequest>(movieRequest,headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
    }

    public void register(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/registration";
        User obj = new User();
        obj.setName("Joshua");
        obj.setLastName("Sellers");
        obj.setUsername("Suupa");
        obj.setPassword("bulldogs1");
        obj.setEmail("joshua.sellersdq@gmail.com");
        HttpEntity<User> requestEntity = new HttpEntity<>(obj, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());
    }

//    public void login(){
//
//        // Create a new RestTemplate instance
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8080/registration";
//        User obj = new User();
//        obj.setName("Joshua");
//        obj.setLastName("Sellers");
//        obj.setUsername("Suupa");
//        obj.setPassword("bulldogs1");
//        obj.setEmail("joshua.sellersdq@gmail.com");
//
//        HttpEntity<User> requestEntity = new HttpEntity<>(obj, headers);
//        try{
//            URI uri = restTemplate.postForLocation(url, requestEntity);
//        }catch (HttpClientErrorException e){
//            System.out.println(e.getStatusCode());
//            System.out.println(e.getResponseBodyAsString());
//        }
//
//    }
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

    public void getAllMovieRequests(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/data/movieRequests";
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<MovieRequest[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, MovieRequest[].class);
        MovieRequest[] movieRequests = responseEntity.getBody();
        for(MovieRequest request : movieRequests){
            System.out.println("userId: "  + request.getUserId() + " from: " +
                    request.getFromUser() + " time in millis " +
                    request.getTimeSent());
        }
    }
    public void getAllFriendPais(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/data/friends";
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Friend[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Friend[].class);
        Friend[] friendRequests = responseEntity.getBody();

        for(Friend friend : friendRequests){
            System.out.println("User1: " + friend.getUserOne() + " User2: " + friend.getUserTwo());
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
        util.addMovieRequest();
        util.getAllMovieRequests();
//        util.login();
//        util.getAllRequestsDemo();
//        util.addArticleDemo();
//        util.getAllArticlesDemo();
//        util.sendFriendRequestDemo();
//        util.addUser();
    }
}
