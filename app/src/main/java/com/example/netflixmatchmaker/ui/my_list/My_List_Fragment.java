package com.example.netflixmatchmaker.ui.my_list;

import android.content.ClipData;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.netflixmatchmaker.CardStackAdapter;
import com.example.netflixmatchmaker.FriendModel;
import com.example.netflixmatchmaker.Friends;
import com.example.netflixmatchmaker.ItemModel;
import com.example.netflixmatchmaker.R;
import com.example.netflixmatchmaker.ui.explore_movies.Explore_Movies_Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class My_List_Fragment extends Fragment {

    private My_List_ViewModel homeViewModel;
    private View result=null;
    private ArrayList<ItemModel> movies = new ArrayList();
    ListView movie_list;
    MovieAdapter movieAdapter;
    Boolean flag=false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(My_List_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my_list, container, false);
        final TextView textView = root.findViewById(R.id.text_my_list);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        movie_list=root.findViewById(R.id.MovieView);
//        userAdapter= new UserAdapter(getActivity());
        movie_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (flag==false){
                    flag=true;

                }
                else{
                    flag=false;

                }
                movieAdapter.selectedItem(position);

                movieAdapter.notifyDataSetChanged();
            }
        });
        callAPI();
        return root;

    }

    private void callAPI() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        String URL = "https://cuddlebug-api.herokuapp.com/liked/"+userId ;
        new AsycnGetLiked().execute(URL);
    }

    public class AsycnGetLiked extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                String line;
                StringBuilder builder = new StringBuilder("");

                while ((line = reader.readLine()) != null)
                    builder.append(line);

                return builder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }
        protected void onPostExecute(String s) {
            if (s != null && s != "") {
                try {

                    JSONObject o = new JSONObject(s);
                    JSONArray a = o.getJSONArray("data");

                    for (int i = 0; i < a.length(); i++) {
                        JSONObject obj =  a.getJSONObject(i);
                        ItemModel item = new ItemModel(obj.getString("Poster"), obj.getString("Title"), obj.getString("Year"), "8.8",obj.getString("imdbID") );

                        movies.add(item);
                    }

                    movieAdapter = new MovieAdapter(getActivity());
                    movie_list.setAdapter(movieAdapter);
                    movieAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public class MovieDetailsQuery extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                String line;
                StringBuilder builder = new StringBuilder("");

                while ((line = reader.readLine()) != null)
                    builder.append(line);

                return builder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        protected void onPostExecute(String s) {
            if (s != null && s != "") {
                try {

                    JSONObject o = new JSONObject(s);

                    String imdbRating = o.getString("imdbRating");
                    String actors = o.getString("Actors");
                    String runTime = o.getString("Runtime");
                    String genre = o.getString("Genre");
                    String plot = o.getString("Plot");
//                    TextView plot_text =(TextView)result.findViewById(R.id.movie_text);
//                    plot_text.setText(plot);


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }


    private class MovieAdapter extends ArrayAdapter<ItemModel> {
        int position;
        public MovieAdapter(Context ctx){
            super(ctx,0);
        }
        public int getCount(){

            return movies.size();
        }
        public void selectedItem(int position){
            this.position=position;
        }
        public ItemModel getItem(int position){
            return movies.get(position);
        }

        public View getView (int position, View convertView, ViewGroup parent){
            final LayoutInflater inflater= getActivity().getLayoutInflater();
//            if (extended=true){
//                result=inflater.inflate(R.layout.movie_list_extended,null);
//
//
//            }else{
//                result=inflater.inflate(R.layout.movie_list,null);
//
//            }


            // If the listView is selected and details not open, fetch the respective movie data
            if (flag==true && this.position==position){
                result=inflater.inflate(R.layout.movie_list_extended,null);

                String URL = "https://www.omdbapi.com/?apikey=a9dd353b&i=" + movies.get(position).getImdbId();
                new MovieDetailsQuery().execute(URL);
            }
            else{
                result=inflater.inflate(R.layout.movie_list,null);
            }


            TextView movie=(TextView)result.findViewById(R.id.movie_text);
            TextView year=(TextView)result.findViewById(R.id.year_text);

            movie.setText(getItem(position).getTitle());
            year.setText(getItem(position).getYear());

            return result;

        }

    }
}