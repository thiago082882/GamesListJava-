package com.thiago.games_java.view;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.palette.graphics.Palette;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.thiago.games_java.R;
import com.thiago.games_java.databinding.FragmentDetailBinding;
import com.thiago.games_java.service.model.GamesItem;
import com.thiago.games_java.service.model.GamePalette;


public class DetailFragment extends Fragment {

    private GamesItem gamesItem;
    FragmentDetailBinding binding;

    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() != null) {
            gamesItem = DetailFragmentArgs.fromBundle(getArguments()).getGamesItem();
        }

        if(gamesItem != null) {
            binding.setGames(gamesItem);
            setupBackgroundColor(gamesItem.imgUrl);
        }
    }

    private void setupBackgroundColor(String url) {
        Glide.with(this)
                .asBitmap()
                .load(url)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Palette.from(resource)
                                .generate(palette -> {
                                    assert palette != null;
                                    Palette.Swatch color = palette.getLightMutedSwatch();
                                    if(color != null) {
                                        int intColor = color.getRgb();
                                        GamePalette gamePalette = new GamePalette(intColor);
                                        binding.setPalette(gamePalette);
                                    }
                                });
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }
}