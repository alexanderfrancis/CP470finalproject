package com.example.netflixmatchmaker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toolbar;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class ExploreActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_mylist, R.id.navigation_explore_movies, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        Log.i("ExploreActivity.java","message");

        Bundle bundle=new Bundle();
        bundle.putString("message", "From Activity");
        //set Fragmentclass Arguments
        MoviesFriendFragment fragobj=new MoviesFriendFragment();
        fragobj.setArguments(bundle);


//        drawerLayout= findViewById(R.id.drawer_layout);
//        navigationView=findViewById(R.id.navigation);
////        toolbar=findViewById(R.id.toolbar);
//
//        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//        navigationView.setNavigationItemSelectedListener(this);


//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }else{
//            onBackPressed();
//
//        }


//
//        ImageView openMenu= findViewById(R.id.openMenu);
//        openMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                openDrawer(drawerLayout);
//
//            }
//        });



    }
    public void replaceFragments(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment)
                .commit();
    }



//    private static void openDrawer(DrawerLayout drawerLayout){
//        drawerLayout.openDrawer(GravityCompat.START);
//    }
//    public void ClickLogo(View view){
//        closeDrawer(drawerLayout);
//    }
//    public void Logout(View view){
//        logout(ExploreActivity.this);
//    }
//
//
//    private static void closeDrawer(DrawerLayout drawerLayout){
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
//            drawerLayout.closeDrawer(GravityCompat.START);
//
//        }
//
//    }
//    private static void logout(final Activity activity){
//        AlertDialog.Builder builder= new AlertDialog.Builder(activity);
//
//        builder.setTitle("Logout");
//
//        builder.setMessage("Are you sure you want to logout?");
//
//        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                activity.finishAffinity();
//                System.exit(0);
//            }
//        });
//
//        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//
//            }
//        });
//        builder.show();
//
//
//
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        closeDrawer(drawerLayout);
//    }
//
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch(item.getItemId()){
//            case(R.id.nav_logout):
//                logout(ExploreActivity.this);
//
//
//                break;
//        }
//        return true;
//    }
}