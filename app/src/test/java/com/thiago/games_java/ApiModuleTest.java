package com.thiago.games_java;


import com.thiago.games_java.di.ApiModule;
import com.thiago.games_java.service.api.GamesApi;
import com.thiago.games_java.service.api.GamesApiService;
import com.thiago.games_java.service.model.GamesItem;

import java.util.List;

import io.reactivex.Single;

public class ApiModuleTest extends ApiModule {

    GamesApiService mockService;

    public ApiModuleTest(GamesApiService mockService) {
        this.mockService = mockService;
    }


}
