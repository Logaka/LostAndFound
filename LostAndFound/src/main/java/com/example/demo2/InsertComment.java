package com.example.demo2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.demo2.DatabaseInfo.*;

public class InsertComment {

    public void saveCommentForPost(int postId, Comment comment) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO commentaries (comtext, email, id) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, comment.getText());
                preparedStatement.setString(2, comment.getAuthor());
                preparedStatement.setInt(3, postId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}