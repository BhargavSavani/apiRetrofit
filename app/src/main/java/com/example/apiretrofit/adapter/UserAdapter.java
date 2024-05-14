package com.example.apiretrofit.adapter;

import android.annotation.SuppressLint;
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
import com.example.apiretrofit.ui.DetaliItemActivity;
import com.example.apiretrofit.model.UserModel;
import com.example.apiretrofit.ui.UserDataHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    Context context;
    List<UserModel> allUserList;
    public UserAdapter(Context context, List<UserModel> allUserList) {
        this.context = context;
        this.allUserList = allUserList;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        holder.itemTitle.setText(allUserList.get(position).getTitle());
        holder.itemPrice.setText("M.R.P : " + "$" + allUserList.get(position).getPrice());
        holder.itemRating.setRating(allUserList.get(position).getRating().getRate());

        Picasso.get().load(allUserList.get(position).getImage()).into(holder.itemImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserDataHolder.setUserData(allUserList.get(position));

                Intent intent = new Intent(context, DetaliItemActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allUserList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle, itemPrice;
        ImageView itemImage;
        RatingBar itemRating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemRating = itemView.findViewById(R.id.itemRating);
        }
    }
}
