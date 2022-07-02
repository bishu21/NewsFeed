package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Data
@RequiredArgsConstructor
public class User {
    String userId;
    String name;
    String phoneNo;
    List<News> news = new ArrayList<>();
    Set<String> subscribedUser = new TreeSet<>();
}
