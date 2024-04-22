package com.thiago.games_java.di;

import com.thiago.games_java.viewmodel.ListViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ApiModule.class, AppModule.class})
@Singleton
public interface ViewModelComponent {
    void inject(ListViewModel viewModel);
}
