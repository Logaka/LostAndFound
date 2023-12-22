package com.example.demo2;

import com.example.demo2.Check.alertException;

public class ConcretePostFactory implements PostFactory{
    @Override
    public Post createPost(int postId, String status, String imagePath, String comment, String userEmail) {
        if (status == null || imagePath == null || comment == null|| userEmail == null){
            new alertException().setAlert("Image, status type, and comment are required");
        }
        return new Post(postId, status, imagePath, comment, userEmail);
    }
}
