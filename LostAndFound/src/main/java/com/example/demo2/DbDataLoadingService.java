package com.example.demo2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo2.DatabaseInfo.*;

public class DbDataLoadingService implements DataLoadingService{
    @Override
    public List<Post> loadPost() {
        List<Post> posts = new ArrayList<>();

        DataGetter dataGetter = new DataGetter();

        for (int i = 1; i <= dataGetter.getCount("posts"); i++) {
            Post post =
                    new Post(
                            i,
                            dataGetter.getStringData("posts", i, "status"),
                            dataGetter.getStringData("posts", i, "imagePath"),
                            dataGetter.getStringData("posts", i, "comment"),
                            dataGetter.getStringData("posts", i, "email")
                    );
            posts.add(post);
        }
        return posts;
    }
    public List<Comment> loadCommentsForPost(int postId) {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM commentaries WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, postId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String text = resultSet.getString("comtext");
                    String author = resultSet.getString("email");
                    Comment comment = new Comment(text, author);
                    comments.add(comment);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
    }
}
