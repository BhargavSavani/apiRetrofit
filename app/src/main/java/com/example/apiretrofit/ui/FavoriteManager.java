package com.example.apiretrofit.ui;

import com.example.apiretrofit.model.FavoriteItemModel;
import com.example.apiretrofit.model.UserModel;

import java.util.ArrayList;
import java.util.List;
public class FavoriteManager {
    private static List<FavoriteItemModel> favorites = new ArrayList<>();
    public static void addToFavorites(UserModel item) {
        favorites.add(new FavoriteItemModel(item));
    }
    public static void removeFromFavorites(UserModel item) {
        for (FavoriteItemModel favorite : favorites) {
            if (favorite.getItem().getId() == item.getId()) {
                favorites.remove(favorite);
                break;
            }
        }
    }
    public static List<FavoriteItemModel> getFavorites() {
        return favorites;
    }

}
