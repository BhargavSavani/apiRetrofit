package com.example.apiretrofit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apiretrofit.R;
import com.example.apiretrofit.model.FavoriteItemModel;
import com.example.apiretrofit.model.UserModel;
import com.example.apiretrofit.ui.DetaliItemActivity;
import com.example.apiretrofit.ui.UserDataHolder;
import com.squareup.picasso.Picasso;

import java.util.List;
public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    List<FavoriteItemModel> favoriteList;
    Context context;
    OnFavoriteItemClickListener listener;
    public FavoriteAdapter(Context context, List<FavoriteItemModel> favoriteList) {
        this.context = context;
        this.favoriteList = favoriteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavoriteItemModel favoriteItem = favoriteList.get(position);
        UserModel userModel = favoriteItem.getItem();

        holder.itemTitle.setText(userModel.getTitle());
        holder.itemPrice.setText("M.R.P : " + "$" + userModel.getPrice());
        holder.itemRating.setRating(userModel.getRating().getRate());

        Picasso.get().load(userModel.getImage()).into(holder.itemImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserDataHolder.setUserData(favoriteList.get(position).getItem());

                Intent intent = new Intent(context, DetaliItemActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public void updateData(List<FavoriteItemModel> favorites) {
        favoriteList.clear();
        favoriteList.addAll(favorites);
        notifyDataSetChanged();
    }

    public interface OnFavoriteItemClickListener {
        void onFavoriteItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle, itemPrice;
        ImageView itemImage;
        RatingBar itemRating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.itemfTitle);
            itemPrice = itemView.findViewById(R.id.itemfPrice);
            itemImage = itemView.findViewById(R.id.itemfImage);
            itemRating = itemView.findViewById(R.id.itemfRating);
        }
    }
}