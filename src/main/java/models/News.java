package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class News implements Comparable<News>{

    Integer id;
    Format format;
    String content;
    String userId;
    LocalDateTime postTime = LocalDateTime.now();
    Integer likes = 0;
    List<Comment> comments = new ArrayList<>();

    @Override
    public int compareTo(News o) {
        if(this.getLikes() == o.getLikes() && o.getPostTime().equals(this.getPostTime())) {
            return o.getComments().size() > this.getComments().size() ? 1: -1;
        } else if (o.getPostTime().equals(this.getPostTime())) {
            return o.getLikes() - this.getLikes();
        }
        return o.getPostTime().isAfter(this.getPostTime()) ? 1 : -1;

    }

    public enum Format {
        TEXT, VIDEO, AUDIO;
    }
}
