package com.fic.soundscape.domain;

import com.fic.soundscape.model.Users;
import java.util.List;
import retrofit2.Callback;

public class Users_Interactor {
    private final Users_Repository users_repository;

    public Users_Interactor(Users_Repository users_repository){
        this.users_repository=users_repository;
    }

    public void login(Users users, Callback<Users> callback) {
        users_repository.login(users, callback);
    }

    public void getUsers(Callback<List<Users>> callback) {
        users_repository.getUsers(callback);
    }

    public void postUsers(Users users, Callback<Users> callback) {
        users_repository.postUsers(users, callback);
    }

}
