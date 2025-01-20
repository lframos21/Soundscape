package com.fic.soundscape.domain;

import com.fic.soundscape.data.Api_Service;
import com.fic.soundscape.model.Chart;
import com.fic.soundscape.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Chart_Repository {

    private final Api_Service api_service;

    public Chart_Repository(Api_Service api_service){
        this.api_service=api_service;
    }

    public void getSongs(Callback<List<Chart>> callback) {
        Call<List<Chart>> call = api_service.getSongs();
        call.enqueue(callback);
    }

}
