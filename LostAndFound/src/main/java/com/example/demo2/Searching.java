package com.example.demo2;

import java.util.ArrayList;
import java.util.List;

public class Searching {
    private DataLoadingProxy dataLoadingProxy;

    public Searching(){
        dataLoadingProxy = DataLoadingProxy.getDataLoadingProxy();
    }

    public List<Post> searchPost(String text){
        List<Post> posts = new ArrayList<>();
        for (Post post : dataLoadingProxy.loadPost()) {
            if(post.getStatus().toLowerCase().contains(text.toLowerCase()))
                posts.add(post);
            else if (post.getComment().toLowerCase().contains(text.toLowerCase())) {
                posts.add(post);
            } else if (post.getAuthor().toLowerCase().contains(text.toLowerCase())) {
                posts.add(post);
            }
        }
        return posts;
    }
}
