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

import com.example.netflixmatchmaker.ui.explore_movies.Explore_Movies_Fragment;
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
import java.util.Collections;


public class AddedFriendsFragment extends Fragment {

    private ArrayList<FriendModel> Friends = new ArrayList();
    ListView friends_list;
    FriendsAdapter friendsAdapter;


    public AddedFriendsFragment() {
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
        View root=inflater.inflate(R.layout.fragment_added_friends, container, false);

        friends_list=root.findViewById(R.id.friendsListView);
        callAPI();

        return root;
    }

    private void callAPI() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();

        String friends_URL = "https://cuddlebug-api.herokuapp.com/friend/" + userId;
        new AsyncGetFriends().execute(friends_URL);


    }
    private class FriendsAdapter extends ArrayAdapter<FriendModel> {

        public FriendsAdapter(Context ctx){
            super(ctx,0);
        }
        public int getCount(){

            return Friends.size();
        }
        public FriendModel getItem(int position){
            return Friends.get(position);
        }

        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater inflater= getActivity().getLayoutInflater();
            View result=null;

            result=inflater.inflate(R.layout.friends_list,null);

            TextView name=(TextView)result.findViewById(R.id.name_text);
            TextView email=(TextView)result.findViewById(R.id.email_text);

            name.setText(getItem(position).getName());
            email.setText(getItem(position).getEmail());

            return result;

        }

    }

    public class AsyncGetFriends extends AsyncTask<String, Void, String> {
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
                        FriendModel item = new FriendModel(obj.getString("friendName"),null,obj.getString("friendId"));


                        Friends.add(item);
                    }
                    friendsAdapter = new FriendsAdapter(getActivity());
                    friends_list.setAdapter(friendsAdapter);
                    friendsAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
}