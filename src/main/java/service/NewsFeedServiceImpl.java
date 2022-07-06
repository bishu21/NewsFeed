package service;

import models.Comment;
import models.News;
import models.User;

import java.util.*;
import java.util.stream.Collectors;

public class NewsFeedServiceImpl implements NewsFeedService {

    Map<String, User> userMap = new HashMap<>();
    Map<Integer, String> newsUserMap = new HashMap<>();
    Integer count=1;


    @Override
    public void register(User user) {
        if (userMap.containsKey(user.getUserId())) {
            // can not register same user again
            throw new RuntimeException("can not register the same user again.");
        }
        userMap.put(user.getUserId(), user);
    }

    @Override
    public void subscribe(String userId1, String userId2) {

        if(!userMap.containsKey(userId1) || !userMap.containsKey(userId2)) {
           // throw user not register exception
            throw new RuntimeException("Users are not found in the system "+ userId1+ " or " +userId2);
        }

        userMap.get(userId1).getSubscribedUser().add(userId2);
        userMap.get(userId2).getSubscribedUser().add(userId1);
    }

    @Override
    public void publishPost(News news) {
        if (!userMap.containsKey(news.getUserId())) {
            // invalid user publishing the post
            throw new RuntimeException("User is not registed to post the news");
        }
        newsUserMap.put(count, news.getUserId());
        news.setId(count++);
        User user = userMap.get(news.getUserId());
        user.getNews().add(news);
    }

    @Override
    public void printNewsFeed(String userId) {
        User user = userMap.get(userId);
        Set<News> news = user.getSubscribedUser().stream().flatMap(item -> userMap.get(item).getNews().stream())
                .collect(Collectors.toSet());

        System.out.println("News feed for the user="+userId);
        System.out.println(new TreeSet<>(news));
    }

    @Override
    public void getOwnNews(String userId) {
        System.out.println("Own Posts/ News for the user="+userId);
        System.out.println(userMap.get(userId).getNews());
    }

    @Override
    public void likePost(Integer newsId) {
        String postUserId = newsUserMap.get(newsId);
        Optional<News> news = userMap.get(postUserId).getNews().stream().filter(item -> item.getId() == newsId).findFirst();

        if(!news.isPresent()) {
            // throw no newsId found for userId
            throw new RuntimeException("newsId = "+ newsId+" not found in the system");
        }

        news.get().setLikes(news.get().getLikes() +1);

    }

    @Override
    public void disLikePost(Integer newsId) {
        String postUserId = newsUserMap.get(newsId);
        Optional<News> news = userMap.get(postUserId).getNews().stream().filter(item -> item.getId() == newsId).findFirst();

        if(!news.isPresent()) {
            // throw no newsId found for userId
            throw new RuntimeException("newsId = "+ newsId+" not found in the system");
        }

        news.get().setLikes(news.get().getLikes() -1);
    }

    @Override
    public void commentOnPost(Comment comment, Integer newsId) {
        String postUserId = newsUserMap.get(newsId);
        Optional<News> news = userMap.get(postUserId).getNews().stream().filter(item -> item.getId() == newsId).findFirst();

        if(!news.isPresent()) {
            // throw no newsId found for userId
            throw new RuntimeException("newsId = "+ newsId+" not found in the system");
        }

        news.get().getComments().add(comment);

    }
}
