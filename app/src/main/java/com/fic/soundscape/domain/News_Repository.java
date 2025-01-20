package com.fic.soundscape.domain;

import com.fic.soundscape.data.Api_Service;
import com.fic.soundscape.model.Blog;
import com.fic.soundscape.model.New;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class News_Repository {
    private final Api_Service api_service;

    public News_Repository(Api_Service api_service){
        this.api_service=api_service;
    }

    public void getNews(Callback<List<New>> callback) {
        Call<List<New>> call = api_service.getNews();
        call.enqueue(callback);
    }
}
