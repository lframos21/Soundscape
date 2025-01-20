package com.fic.soundscape.domain;

import com.fic.soundscape.model.Blog;

import java.util.List;

import retrofit2.Callback;

public class Blogs_Interactor {
    private final Blogs_Repository blogs_repository;

    public Blogs_Interactor(Blogs_Repository blogsRepository) {
        blogs_repository = blogsRepository;
    }

    public void getBlogs(Callback<List<Blog>> callback) {
        blogs_repository.getBlogs(callback);
    }
}
