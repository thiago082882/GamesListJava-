package com.thiago.games_java.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.thiago.games_java.R;
import com.thiago.games_java.databinding.ItemGamesBinding;
import com.thiago.games_java.service.model.GamesItem;
import com.thiago.games_java.view.GamesClickListener;
import com.thiago.games_java.view.GamesFragmentDirections;


import java.util.ArrayList;
import java.util.List;

public class GamesListAdapter extends RecyclerView.Adapter<GamesListAdapter.GamesViewHolder> implements GamesClickListener {

    private final List<GamesItem> gamesList  = new ArrayList<>();


    public void updateGameList(List<GamesItem> gamesItemList) {
        gamesList .clear();
        gamesList.addAll(gamesItemList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemGamesBinding view = DataBindingUtil.inflate(inflater, R.layout.item_games, parent, false);
        return new GamesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesViewHolder holder, int position) {
        holder.itemView.setGames(gamesList.get(position));
        holder.itemView.setListener(this);
    }

    @Override
    public void onClick(View v) {
        for (GamesItem games : gamesList) {
            if(v.getTag().equals(games.title)) {
                NavDirections action = (NavDirections) GamesFragmentDirections.actionGamesFragmentToDetailFragment(games);
                Navigation.findNavController(v).navigate(action);
            }
        }
    }

    @Override
    public int getItemCount() {
        return gamesList.size();
    }

    class GamesViewHolder extends RecyclerView.ViewHolder {

        ItemGamesBinding itemView;

        public GamesViewHolder(ItemGamesBinding view) {
            super(view.getRoot());
            itemView = view;
        }
    }
}
