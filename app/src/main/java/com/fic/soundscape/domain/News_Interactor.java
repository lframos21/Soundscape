package com.fic.soundscape.domain;

import com.fic.soundscape.model.New;

import java.util.List;

import retrofit2.Callback;

public class News_Interactor {
    private final News_Repository news_repository;

    public News_Interactor(News_Repository newsRepository) {
        news_repository = newsRepository;
    }

    public void getNews(Callback<List<New>> callback) {
        news_repository.getNews(callback);
    }
}
