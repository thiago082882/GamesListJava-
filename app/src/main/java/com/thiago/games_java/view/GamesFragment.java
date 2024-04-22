package com.thiago.games_java.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thiago.games_java.R;
import com.thiago.games_java.adapter.GamesListAdapter;
import com.thiago.games_java.databinding.FragmentGamesBinding;

import com.thiago.games_java.service.model.GamesItem;
import com.thiago.games_java.viewmodel.ListViewModel;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviderGetKt;
import androidx.recyclerview.widget.GridLayoutManager;


import java.util.ArrayList;
import java.util.List;


public class GamesFragment extends Fragment {

    FragmentGamesBinding binding;

    private ListViewModel viewModel;

    List<GamesItem> gamesList = new ArrayList<>();

    private GamesListAdapter gamesListAdapter = new GamesListAdapter();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentGamesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ListViewModel.class);

        viewModel.games.observe(getViewLifecycleOwner(), list -> {
            if (list != null) {
                binding.gameList.setVisibility(View.VISIBLE);
                gamesListAdapter.updateGameList(list);
            }
        });
        viewModel.loading.observe(getViewLifecycleOwner(), loading -> {
            if(loading !=null) {
                binding.loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
                if (loading) {
                    binding.listError.setVisibility(View.GONE);
                    binding.gameList.setVisibility(View.GONE);
                }
            }
        });

        viewModel.loadError.observe(getViewLifecycleOwner(), error -> { // Updated line
            binding.listError.setVisibility(error ? View.VISIBLE : View.GONE);
        });

        viewModel.refresh();

        if(gamesList != null) {
            binding.gameList.setLayoutManager(new GridLayoutManager(getContext(), 2));
            binding.gameList.setAdapter(gamesListAdapter);
        }

        binding.gameRefreshLayout.setOnRefreshListener(() -> {
            binding.gameList.setVisibility(View.GONE);
            binding.listError.setVisibility(View.GONE);
            binding.loadingView.setVisibility(View.VISIBLE);
            viewModel.hardRefresh();
            viewModel.loading.setValue(false);
            binding.gameRefreshLayout.setRefreshing(false);
            binding.gameList.setVisibility(View.VISIBLE);
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Avoid memory leaks


    }
}