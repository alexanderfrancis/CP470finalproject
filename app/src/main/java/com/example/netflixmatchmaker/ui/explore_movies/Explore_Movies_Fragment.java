package com.example.netflixmatchmaker.ui.explore_movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.netflixmatchmaker.R;

public class Explore_Movies_Fragment extends Fragment {

    private Explore_Movies_ViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(Explore_Movies_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_explore_movies, container, false);
        final TextView textView = root.findViewById(R.id.text_explore_movies);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}