package com.example.demo2;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private String status;
    private String imagePath;
    private String comment;
    private String userEmail;
    private List<Comment> comments;
    private int postid;

    public Post(int postid,String status, String imagePath, String comment, String userEmail) {
        this.status = status;
        this.imagePath = imagePath;
        this.comment = comment;
        this.userEmail = userEmail;
        this.comments = new ArrayList<>();
        this.postid = postid;
    }

    public String getStatus() {
        return status;
    }

    public int getPostid() {
        return postid;
    }

    public String getImagePath() {
        return imagePath;
    }
    public String getComment() {
        return comment;
    }

    public String getAuthor() {
        return userEmail;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setStatusType(String statusType) {
        this.status = statusType;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public void setAuthor(String author) {
        this.userEmail = userEmail;
    }
    public List<Comment> getComments(){
        return new ArrayList<>(comments);
    }
    public void addComment(Comment comment){
        comments.add(comment);
    }
};