package com.example.apiretrofit.fragment;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apiretrofit.adapter.UserAdapter;
import com.example.apiretrofit.databinding.FragmentHomeBinding;
import com.example.apiretrofit.model.UserModel;
import com.example.apiretrofit.ui.GridItemDecoration;
import com.example.apiretrofit.ui.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    List<UserModel> allUserList =new ArrayList<>();
    private UserAdapter useradapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(LayoutInflater.from(requireContext()),container,false);

        binding.rcvMain.setLayoutManager(new GridLayoutManager(requireContext(),2));
        Resources resources = getResources();
        GridItemDecoration itemDecoration = new GridItemDecoration(resources);

        binding.rcvMain.addItemDecoration(itemDecoration);
        useradapter = new UserAdapter(requireContext(), allUserList);
        binding.rcvMain.setAdapter(useradapter);

        RetrofitInstance.getInstance().apiInterface.getUser().enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    allUserList.clear();
                    allUserList.addAll(response.body());
                    useradapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                Log.e("api", "onFailure: " + t.getLocalizedMessage());
            }
        });
        return binding.getRoot();
    }
}