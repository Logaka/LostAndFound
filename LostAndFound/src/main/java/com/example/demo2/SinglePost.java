package com.example.demo2;

public class SinglePost {
    private static SinglePost singlePost;
    private int postid;
    private String status;
    private String imagepath;
    private String comment;
    private String email;
    private SinglePost() {}
    public static SinglePost getSinglePost(){
        if(singlePost == null){
            return singlePost = new SinglePost();}
        return singlePost;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPostid() {
        return postid;
    }
    public String getStatus(){
        return status;
    }

    public String getImagepath() {
        return imagepath;
    }

    public String getComment() {
        return comment;
    }

    public String getEmail() {
        return email;
    }
}