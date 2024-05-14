package com.example.apiretrofit.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apiretrofit.R;
import com.example.apiretrofit.model.FavoriteItemModel;
import com.example.apiretrofit.model.UserModel;
import com.squareup.picasso.Picasso;

public class DetaliItemActivity extends AppCompatActivity {
    private UserModel userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detali_item);

        userData = UserDataHolder.getUserData();

        if (userData != null) {
            ImageView itemImage = findViewById(R.id.detailItemImage);
            TextView itemTitle = findViewById(R.id.detailItemTitle);
            RatingBar itemRating = findViewById(R.id.detailItemRating);
            TextView itemPrice = findViewById(R.id.detailItemPrice);
            TextView itemCategory = findViewById(R.id.detailItemCategory);
            TextView itemDescription = findViewById(R.id.detailItemDescription);

            Picasso.get().load(userData.getImage()).into(itemImage);
            itemTitle.setText(userData.getTitle());
            itemRating.setRating(userData.getRating().getRate());
            itemPrice.setText("M.R.P: ₹" + userData.getPrice());
            itemCategory.setText("Category: " + userData.getCategory());
            itemDescription.setText("Description: " + userData.getDescription());
        }
        updateFavoriteIcon();
    }
    public void toggleFavorite(View view) {
        if (userData != null) {
            if (isFavorite(userData)) {
                removeFromFavorites(userData);
                Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
            } else {
                addToFavorites(userData);
                Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
            }
            updateFavoriteIcon();
        }
    }
    private boolean isFavorite(UserModel item) {
        for (FavoriteItemModel favorite : FavoriteManager.getFavorites()) {
            if (favorite.getItem().getId() == item.getId()) {
                return true;
            }
        }
        return false;
    }
    private void addToFavorites(UserModel item) {
        FavoriteManager.addToFavorites(item);
    }

    private void removeFromFavorites(UserModel item) {
        FavoriteManager.removeFromFavorites(item);
    }
    private void updateFavoriteIcon() {
        ImageView favoriteIcon = findViewById(R.id.favoriteIcon);
        if (isFavorite(userData)) {
            favoriteIcon.setImageResource(R.drawable.baseline_favorite);
        } else {
            favoriteIcon.setImageResource(R.drawable.baseline_favorite_border);
        }
    }
}
