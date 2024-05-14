package com.example.apiretrofit.fragment;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apiretrofit.R;
import com.example.apiretrofit.adapter.FavoriteAdapter;

import com.example.apiretrofit.ui.FavoriteManager;
import com.example.apiretrofit.ui.GridItemDecoration;


public class FavouriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private FavoriteAdapter favoriteAdapter;
    private TextView textNoFavorite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewFavorites);
        textNoFavorite = view.findViewById(R.id.textNoFavorite);

        if (FavoriteManager.getFavorites().isEmpty()) {
            textNoFavorite.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            textNoFavorite.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            Resources resources = getResources();
            GridItemDecoration itemDecoration = new GridItemDecoration(resources);
            recyclerView.addItemDecoration(itemDecoration);
            favoriteAdapter = new FavoriteAdapter(requireContext(), FavoriteManager.getFavorites());
            recyclerView.setLayoutManager(new GridLayoutManager(requireContext(),2));
            recyclerView.setAdapter(favoriteAdapter);
        }

        return view;
    }
}