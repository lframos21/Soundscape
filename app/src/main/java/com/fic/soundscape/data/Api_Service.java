package com.fic.soundscape.data;

import com.fic.soundscape.model.Blog;
import com.fic.soundscape.model.Chart;
import com.fic.soundscape.model.New;
import com.fic.soundscape.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api_Service {

    @POST("loginsoundscape")
    Call<Users> login(@Body Users users);

    @GET("listausuarios")
    Call <List<Users>>getUsers();

    @POST("registersoundscape")
    Call <Users> postUsers(@Body Users users);

    @GET("listacanciones")
    Call <List<Chart>>getSongs();

    @GET("listablogs")
    Call <List<Blog>>getBlogs();

    @GET("listanoticias")
    Call <List<New>>getNews();

}