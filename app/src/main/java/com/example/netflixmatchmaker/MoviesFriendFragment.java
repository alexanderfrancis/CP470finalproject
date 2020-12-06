package com.example.netflixmatchmaker;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MoviesFriendFragment extends Fragment {

    private ArrayList<ItemModel> Movies = new ArrayList();
    ListView movie_list;
    MovieAdapter movieAdapter;
    ItemModel add_friend;

    public MoviesFriendFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String friendName=getArguments().getString("friendName");
        String friendId=getArguments().getString("friendId");


        View root= inflater.inflate(R.layout.fragment_movies_friend, container, false);
        movie_list= root.findViewById(R.id.MovieViewFriends);
        callAPI(friendId);


        return root;
    }
    private void callAPI(String friendId) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();

        String friends_URL = "https://cuddlebug-api.herokuapp.com/liked/?user1=" + userId + "&user2="+friendId;

        new AsyncGetCommonMovies().execute(friends_URL);
    }
    private class MovieAdapter extends ArrayAdapter<ItemModel> {

        public MovieAdapter(Context ctx){
            super(ctx,0);
        }
        public int getCount(){

            return Movies.size();
        }
        public ItemModel getItem(int position){
            return Movies.get(position);
        }

        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater inflater= getActivity().getLayoutInflater();
            View result=null;

            result=inflater.inflate(R.layout.movie_list,null);

            TextView name=(TextView)result.findViewById(R.id.movie_text);
            TextView year=(TextView)result.findViewById(R.id.year_text);

            name.setText(getItem(position).getTitle());
            year.setText(getItem(position).getYear());

            return result;

        }

    }


    public class AsyncGetCommonMovies extends AsyncTask<String, Void, String> {
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
                        ItemModel item = new ItemModel(obj.getString("Poster"), obj.getString("Title"), obj.getString("Year"), "8.8",obj.getString("imdbID"));


                        Movies.add(item);
                    }
                    movieAdapter= new MovieAdapter(getActivity());
                    movie_list.setAdapter(movieAdapter);
                    movieAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }


}