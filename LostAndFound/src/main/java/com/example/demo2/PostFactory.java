package com.example.demo2;

public interface PostFactory {
    Post createPost(int postId, String status, String imagePath, String comment, String userEmail) throws IllegalAccessException;
}
