package service;

import models.Comment;
import models.News;
import models.User;

public interface NewsFeedService {

    void register(User user);
    void subscribe(String userId1, String userId2);

    void publishPost(News news);

    void printNewsFeed(String userId);
    void getOwnNews(String userId);

    void likePost(Integer newsId);
    void disLikePost(Integer newsId);

    void commentOnPost(Comment comment, Integer newsId);

}
