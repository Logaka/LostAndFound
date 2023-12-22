package com.example.demo2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataLoadingProxy implements DataLoadingService{
    private static DataLoadingProxy dataLoadingProxy;
    private DataLoadingService dataLoadingService;
    private Map<Integer, List<Post>> cache;
    private DataGetter dataGetter;

    private DataLoadingProxy(){
        this.dataLoadingService = new DbDataLoadingService();
        this.cache = new HashMap<>();
        this.dataGetter = new DataGetter();
    }

    public static DataLoadingProxy getDataLoadingProxy(){
        if(dataLoadingProxy == null)
            dataLoadingProxy = new DataLoadingProxy();
        return dataLoadingProxy;
    }
    @Override
    public List<Post> loadPost() {
        // unique key
        int cacheKey = dataGetter.getCount("posts");

        // return if data exist
        if (cache.containsKey(cacheKey)) {
            System.out.println("Get from Cache");
            return cache.get(cacheKey);
        }

        System.out.println("Get from Database");

        // safe if data does not exist
        List<Post> posts = dataLoadingService.loadPost();

        //put the data to cache
        cache.put(cacheKey, posts);

        return posts;
    }
}
