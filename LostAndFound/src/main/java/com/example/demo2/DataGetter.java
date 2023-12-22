package com.example.demo2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo2.DatabaseInfo.*;

public class DataGetter {
    public String getStringData(String table, int id, String column) {
        String data = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT "+column+"  FROM "+ table + " WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    data = resultSet.getString(column);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public int getDataId(String table, String email){
        int id = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT id  FROM "+ table + " WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public Integer getCount(String table) {
        int tableSize = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String countQuery = "SELECT COUNT(*) FROM " + table;
            try (PreparedStatement preparedStatement = connection.prepareStatement(countQuery)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        tableSize = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tableSize;
    }

    public List<Post> getCurrentPost(String email) {
        List<Post> list = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM posts WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String status = resultSet.getString("status");
                        String imagePath = resultSet.getString("imagePath");
                        String comment = resultSet.getString("comment");

                        Post post = new Post(id, status, imagePath, comment, email);
                        list.add(post);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving posts for user " + email, e);
        }

        return list;
    }

}