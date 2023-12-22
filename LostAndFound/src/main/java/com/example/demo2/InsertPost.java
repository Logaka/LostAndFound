package com.example.demo2;

import com.example.demo2.Check.alertException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertPost {
    DataGetter dataGetter;
    User user;
    public InsertPost(){
        this.dataGetter = new DataGetter();
        this.user = User.getUser();
    }
    public void insert(Post post){
        int id = dataGetter.getCount("posts") + 1;
        try (Connection connection = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.DB_USER,DatabaseInfo.DB_PASSWORD)) {
            String insertQuery = "INSERT INTO posts (id, status,imagePath,comment,email)\n" +
                    "VALUES('"+id+"','"+post.getStatus()+"','"+post.getImagePath()+"','"+post.getComment()+"','"+user.getEmail()+"');";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            new alertException().setAlert("Connection Error");
            e.printStackTrace();
        }
    }
}
