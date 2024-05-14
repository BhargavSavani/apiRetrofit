package com.example.apiretrofit.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apiretrofit.adapter.FavoriteAdapter;
import com.example.apiretrofit.fragment.FavouriteFragment;
import com.example.apiretrofit.fragment.CartFragment;
import com.example.apiretrofit.fragment.HomeFragment;
import com.example.apiretrofit.R;
import com.example.apiretrofit.fragment.ProfileFragment;
import com.example.apiretrofit.model.FavoriteItemModel;
import com.example.apiretrofit.model.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    public static String api = "https://fakestoreapi.com";
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    CircleImageView ci1;
    private static final int PICK_IMAGE_REQUEST = 100;
    ImageView ivOPenDrawer;
    TextView tvName, tvEmail;
    BottomNavigationView btNavigation;
    SharedPreferences sharedPreferences;
    FavoriteAdapter favoriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment(new Fragment(), false);

        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String name = sharedPreferences.getString("name", "");
        String email = sharedPreferences.getString("email", "");

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        ivOPenDrawer = findViewById(R.id.ivOpenDrawer);
        btNavigation = findViewById(R.id.btNavigationView);

        tvName = navigationView.getHeaderView(0).findViewById(R.id.tvName);
        tvEmail = navigationView.getHeaderView(0).findViewById(R.id.tvEmail);
        ci1 = navigationView.getHeaderView(0).findViewById(R.id.ci1);

        tvName.setText(name);
        tvEmail.setText(email);

        btNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.Home) {
                    setFragment(new HomeFragment(), true);
                } else if (item.getItemId() == R.id.cart) {
                    setFragment(new CartFragment(), false);
                } else if (item.getItemId() == R.id.favourite) {
                    setFragment(new FavouriteFragment(), false);
                } else {
                    setFragment(new ProfileFragment(), false);
                }
                return true;
            }
        });
        btNavigation.setSelectedItemId(R.id.Home);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.nav_account) {
                    Toast.makeText(MainActivity.this, "My Account", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.nav_settings) {
                    Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Are you sure you want to logout?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        ivOPenDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!drawerLayout.isOpen()) {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        ci1.setOnClickListener(v -> openGallery());
        loadUserData();
    }
    private void loadUserData() {
        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        String email = sharedPreferences.getString("email", "");

        tvName.setText(name);
        tvEmail.setText(email);
    }
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            ci1.setImageURI(selectedImageUri);
        }
    }
    public void setFragment(Fragment fragment, boolean flag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (flag)
            fragmentTransaction.replace(R.id.container, fragment);
        else
            fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit!!!........")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}