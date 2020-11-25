package com.example.netflixmatchmaker.ui.settings;

import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Settings_Fragment extends Fragment {

    private Settings_ViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        TextView displayName;

        notificationsViewModel =
                ViewModelProviders.of(this).get(Settings_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        final TextView textView = root.findViewById(R.id.text_settings);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        FirebaseAuth.getInstance().getCurrentUser();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.i("BNSDFNKLA", "BLAH");
        if(user!=null){
            displayName = (TextView) getView().findViewById(R.id.displayName);
            displayName.setText("Display Name: " + user);
            Log.i("SUP", "LOGGED");
        }else{
            Log.i("SUP", "NOT LOGGED");
        }



        return root;
    }
}