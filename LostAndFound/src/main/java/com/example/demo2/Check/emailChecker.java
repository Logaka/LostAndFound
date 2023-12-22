package com.example.demo2.Check;

import com.example.demo2.DatabaseInfo;

import java.sql.*;

public class emailChecker implements Checker{
    @Override
    public boolean isValid(String text) {
        if(text.isEmpty()) {
            System.out.println("f1");
            return false;

        }
        else if(!(text.endsWith("@gmail.com")) && !(text.endsWith("@mail.ru")) && !(text.endsWith("@stu.sdu.edu.kz"))) {
            System.out.println("f2");
            return false;
        }
        String selectQuery = "SELECT * FROM users WHERE email = ?";
        try (Connection connection = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.DB_USER, DatabaseInfo.DB_PASSWORD)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, text);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    System.out.println("!resultSet.next() "+!resultSet.next());
                    return !resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }


}
