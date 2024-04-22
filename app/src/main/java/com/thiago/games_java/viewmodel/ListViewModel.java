package com.thiago.games_java.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.thiago.games_java.di.AppModule;
import com.thiago.games_java.di.DaggerViewModelComponent;
import com.thiago.games_java.service.api.GamesApiService;
import com.thiago.games_java.service.model.GamesItem;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends AndroidViewModel {

    @Inject
    GamesApiService apiService;

    private final CompositeDisposable disposable = new CompositeDisposable();

    public MutableLiveData<List<GamesItem>> games = new MutableLiveData<>();

    public MutableLiveData<Boolean> loadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();


    Boolean injected = false;

    public ListViewModel(@NonNull Application application) {
        super(application);
    }


    private void inject() {
        if (!injected) {
            DaggerViewModelComponent.builder()
                    .appModule(new AppModule(getApplication()))
                    .build()
                    .inject(this);
        }
    }

    public void refresh() {
        inject();
        loading.setValue(true);

        getGames();

    }

    public void hardRefresh() {
        loading.setValue(true);

    }

    private void getGames() {
        disposable.add(
                apiService.getGames()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<GamesItem>>() {
                            @Override
                            public void onSuccess(List<GamesItem> gamesModels) {
                                games.setValue(gamesModels);
                                loadError.setValue(false);
                                loading.setValue(false);
                            }


                            @Override
                            public void onError(Throwable e) {
                                loadError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

}
