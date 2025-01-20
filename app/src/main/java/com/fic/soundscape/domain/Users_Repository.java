package com.fic.soundscape.domain;

import com.fic.soundscape.data.Api_Service;
import com.fic.soundscape.model.Users;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;

public class Users_Repository {
    private final Api_Service api_service;

    public Users_Repository(Api_Service api_service){
        this.api_service=api_service;
    }

    public void login(Users users, Callback<Users> callback) {
        Call<Users> call = api_service.login(users);
        call.enqueue(callback);
    }

    public void getUsers(Callback<List<Users>> callback) {
        Call<List<Users>> call = api_service.getUsers();
        call.enqueue(callback);
    }

    public void postUsers(Users users, Callback<Users> callback) {
        Call<Users> call = api_service.postUsers(users);
        call.enqueue(callback);
    }

}