package com.thiago.games_java.service.api;


import com.thiago.games_java.service.model.GamesItem;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface GamesApi {

    @GET("/games")
    public Single<List<GamesItem>> getGames();
}
