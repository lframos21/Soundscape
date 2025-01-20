package com.fic.soundscape.domain;

import com.fic.soundscape.data.Api_Service;
import com.fic.soundscape.model.Blog;
import com.fic.soundscape.model.Chart;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Blogs_Repository {
    private final Api_Service api_service;

    public Blogs_Repository(Api_Service api_service){
        this.api_service=api_service;
    }

    public void getBlogs(Callback<List<Blog>> callback) {
        Call<List<Blog>> call = api_service.getBlogs();
        call.enqueue(callback);
    }
}
