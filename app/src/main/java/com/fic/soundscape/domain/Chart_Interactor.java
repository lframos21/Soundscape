package com.fic.soundscape.domain;

import com.fic.soundscape.model.Chart;

import java.util.List;

import retrofit2.Callback;

public class Chart_Interactor {
    private final Chart_Repository chart_repository;

    public Chart_Interactor(Chart_Repository chartRepository) {
        chart_repository = chartRepository;
    }

    public void getSongs(Callback<List<Chart>> callback) {
        chart_repository.getSongs(callback);
    }
}
