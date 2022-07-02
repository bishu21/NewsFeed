import models.Comment;
import models.News;
import models.User;
import service.NewsFeedService;
import service.NewsFeedServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class NewsFeedApp {
    public static void main(String[] args) {
        User user = new User();
        user.setUserId("abc1");
        user.setUserId("Bishwendra");
        user.setPhoneNo("9571777319");

        User user1 = new User();
        user1.setUserId("def1");
        user1.setUserId("Suman");
        user1.setPhoneNo("9571777319");

        User user2 = new User();
        user2.setUserId("qwe1");
        user2.setUserId("Sunita");
        user2.setPhoneNo("9571777319");

        NewsFeedService newsFeedService = new NewsFeedServiceImpl();

        newsFeedService.register(user);
        newsFeedService.register(user1);
        newsFeedService.register(user2);

        newsFeedService.subscribe(user.getUserId(), user1.getUserId());
        newsFeedService.subscribe(user.getUserId(), user2.getUserId());

        News news = new News(null, News.Format.TEXT, "It is raining in Jaipur", user.getUserId(),
                LocalDateTime.now(), 0, new ArrayList<>());

        News news1 = new News(null, News.Format.TEXT, "It is very hot in delhi", user1.getUserId(),
                LocalDateTime.now(), 0, new ArrayList<>());

        News news2 = new News(null, News.Format.TEXT, "It is raining sunny in bangalore", user2.getUserId(),
                LocalDateTime.now(), 0, new ArrayList<>());
        LocalDateTime time = LocalDateTime.now();
        News news3 = new News(null, News.Format.TEXT, "Monsoon has been started ", user2.getUserId(),
                time, 0, new ArrayList<>());
        News news4 = new News(null, News.Format.TEXT, "Going for long trip", user2.getUserId(),
                time, 0, new ArrayList<>());


        newsFeedService.publishPost(news);
        newsFeedService.publishPost(news1);
        newsFeedService.publishPost(news2);
        newsFeedService.publishPost(news3);
        newsFeedService.publishPost(news4);

        newsFeedService.printNewsFeed(user.getUserId());
        newsFeedService.printNewsFeed(user1.getUserId());
        newsFeedService.printNewsFeed(user2.getUserId());

        System.out.println();

        newsFeedService.likePost(1);
        newsFeedService.likePost(1);
        newsFeedService.likePost(1);
        newsFeedService.disLikePost(1);
        newsFeedService.likePost(2);
        newsFeedService.likePost(4);

        Comment comment = new Comment(1, "I am coming to Jaipur", user1.getUserId(), 0, null);
        newsFeedService.commentOnPost(comment, 1);

        newsFeedService.printNewsFeed(user.getUserId());
        newsFeedService.printNewsFeed(user1.getUserId());
        newsFeedService.printNewsFeed(user2.getUserId());

        System.out.println();

        newsFeedService.getOwnNews(user.getUserId());
        newsFeedService.getOwnNews(user1.getUserId());
        newsFeedService.getOwnNews(user2.getUserId());

    }
}
