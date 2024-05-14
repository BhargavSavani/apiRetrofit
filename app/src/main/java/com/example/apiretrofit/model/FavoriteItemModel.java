package com.example.apiretrofit.model;

public class FavoriteItemModel {
    private UserModel item;
    public FavoriteItemModel(UserModel item) {
        this.item = item;
    }

    public UserModel getItem() {
        return item;
    }
}
