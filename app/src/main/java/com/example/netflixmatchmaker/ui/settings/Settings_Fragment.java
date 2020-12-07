package com.example.netflixmatchmaker.ui.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.netflixmatchmaker.AddedFriendsFragment;
import com.example.netflixmatchmaker.ExploreActivity;
import com.example.netflixmatchmaker.Friends;
import com.example.netflixmatchmaker.R;
import com.example.netflixmatchmaker.ui.login.LoginActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Settings_Fragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    private Settings_ViewModel notificationsViewModel;
    NavigationView navigationView;
    Friends friendslist;
    Settings_Fragment activity;
    FragmentManager fragmentManager;
    private FirebaseAuth mAuth;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        TextView displayName;
        TextView userEmail;

        notificationsViewModel =
                ViewModelProviders.of(this).get(Settings_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        final TextView textView = root.findViewById(R.id.text_settings);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        FirebaseAuth.getInstance().getCurrentUser();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.i("BNSDFNKLA", "BLAH");
        if(user!=null){
            displayName = root.findViewById(R.id.userName);
            userEmail = root.findViewById(R.id.userEmail);
            displayName.setText("Display Name:    " + user.getDisplayName());
            userEmail.setText("Email:                   " + user.getEmail());
            Log.i("SUP", "LOGGED");
        }else{
            Log.i("SUP", "NOT LOGGED");
        }
//        mDrawerToggle= new ActionBarDrawerToggle(getActivity(),drawerLayout,0 ,0);
//        mDrawerToggle.syncState();

        drawerLayout= root.findViewById(R.id.drawer_layout_frag);
        navigationView= root.findViewById(R.id.navigation);


        friendslist=new Friends();
        activity=new Settings_Fragment();

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(getActivity(),drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);




        ImageView openMenu= root.findViewById(R.id.openMenu);
        openMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.bringToFront();
                drawerLayout.setScrimColor(Color.TRANSPARENT);

                openDrawer(drawerLayout);

            }
        });

        mAuth = FirebaseAuth.getInstance();


        return root;
    }


    private static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private static void closeDrawer(DrawerLayout drawerLayout){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);

        }

    }



    private void logout(final Activity activity){
        AlertDialog.Builder builder= new AlertDialog.Builder(activity);

        builder.setTitle("Logout");

        builder.setMessage("Are you sure you want to logout?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                mAuth.getInstance().signOut();
                Toast.makeText(getActivity().getApplicationContext(), "Logged Out", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);


            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.show();



    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment fragment = null;

        switch(item.getItemId()){
            case(R.id.nav_logout):
                fragment=new Friends();

                logout(getActivity());
                closeDrawer(drawerLayout);
                break;

            case(R.id.add_friends):

                fragment=new Friends();
                closeDrawer(drawerLayout);

                break;
            case (R.id.friends_list):
                drawerLayout.bringToFront();
                fragment=new AddedFriendsFragment();
                closeDrawer(drawerLayout);
                break;


        }
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment)
                .commit();

        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}