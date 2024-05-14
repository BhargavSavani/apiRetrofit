package com.example.apiretrofit.ui;

import com.example.apiretrofit.model.UserModel;
public class UserDataHolder {
    private static UserModel userData;
    public static UserModel getUserData() {
        return userData;
    }
    public static void setUserData(UserModel userData) {
        UserDataHolder.userData = userData;
    }

}
