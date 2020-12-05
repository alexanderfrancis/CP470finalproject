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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Friends extends Fragment {

    private ArrayList<FriendModel> Users = new ArrayList();
    ListView user_list;
    UserAdapter userAdapter;
    public Friends() {
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
        View root=inflater.inflate(R.layout.fragment_friends, container, false);
//
        user_list=root.findViewById(R.id.friendsView);
//        userAdapter= new UserAdapter(getActivity());
        callAPI();
//
//        user_list.setAdapter(userAdapter);
        return root;
    }

    @Override
    public void onPause() {
        super.onPause();

    }


    private void callAPI() {
        String URL = "https://cuddlebug-api.herokuapp.com/user";
        new Friends.AsycnGet().execute(URL);
    }

    public class AsycnGet extends AsyncTask<String, Void, String> {
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
                        FriendModel item = new FriendModel(obj.getString("name"),obj.getString("email"),obj.getString("uid"));


                        Users.add(item);
                    }
                    userAdapter = new UserAdapter(getActivity());
                    user_list.setAdapter(userAdapter);
                    userAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private class UserAdapter extends ArrayAdapter<FriendModel> {

        public UserAdapter(Context ctx){
            super(ctx,0);
        }
        public int getCount(){

            return Users.size();
        }
        public FriendModel getItem(int position){
            return Users.get(position);
        }

        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater inflater= getActivity().getLayoutInflater();
            View result=null;

            result=inflater.inflate(R.layout.user_list,null);

            TextView name=(TextView)result.findViewById(R.id.name_text);
            TextView email=(TextView)result.findViewById(R.id.email_text);

            name.setText(getItem(position).getName());
            email.setText(getItem(position).getEmail());

            return result;

        }

    }

}


