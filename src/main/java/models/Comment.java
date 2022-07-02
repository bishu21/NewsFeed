package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comment {
    Integer id;
    String content;
    String userId;
    Integer likes;
    Comment comment;
}
