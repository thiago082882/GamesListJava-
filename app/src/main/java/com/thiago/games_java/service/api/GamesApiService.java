package com.thiago.games_java.service.api;


import com.thiago.games_java.service.model.GamesItem;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class GamesApiService {

    @Inject
    GamesApi gamesApi;

    @Inject
    public GamesApiService(GamesApi gamesApi) {
        this.gamesApi = gamesApi;
    }

    public Single<List<GamesItem>> getGames() {
        return gamesApi.getGames();
    }
}
