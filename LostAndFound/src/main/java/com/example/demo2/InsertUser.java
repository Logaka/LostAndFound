package com.example.demo2;
import java.sql.*;
import com.example.demo2.Check.*;
public class InsertUser {
    public void insert(String name,String lastname,String password,String email,String iconpath) {
        try (Connection connection = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.DB_USER,DatabaseInfo.DB_PASSWORD)) {
            String insertQuery = "INSERT INTO users (name,lastname,password,email,iconpath)\n" +
                    "VALUES('"+name+"','"+lastname+"','"+password+"','"+email+"','"+iconpath+"');";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            new alertException().setAlert(e.getMessage());
            e.printStackTrace();

        }
    }
    public void updateData(String column,String newData,String email) {
        try (Connection connection = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.DB_USER, DatabaseInfo.DB_PASSWORD)) {
            String query = "UPDATE users SET " + column + " = ? WHERE email = ?";
            System.out.println("updateQuery: " + query);
            System.out.println(column+" "+newData+" " + email);
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1,newData);
                preparedStatement.setString(2,email);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Update successful. Rows affected: " + rowsAffected);
                } else {
                    System.out.println("No rows updated.");
                }
            }
        } catch (SQLException e) {
            new alertException().setAlert("Database Connections Error");
            System.out.println("updateError");
            e.printStackTrace();
        }
    }
}