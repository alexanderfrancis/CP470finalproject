package com.example.netflixmatchmaker;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Friends extends Fragment {
    FragmentManager fragmentManager;

    private ArrayList<FriendModel> Users = new ArrayList();
    ListView user_list;
    UserAdapter userAdapter;
    FriendModel add_friend;
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



        user_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callAddFriendAPI();

                add_friend= userAdapter.getItem(position);
                userAdapter.remove(userAdapter.getItem(position));
//                LocationList.remove(item);
//                adapter = new MyAdapter(getActivity(),LocationList);
//                list.setAdapter(adapter);
            }

        });



        return root;
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void callAPI() {
        String URL = "https://cuddlebug-api.herokuapp.com/user";
        new Friends.AsycnGet().execute(URL);
    }

    private void callAddFriendAPI(){
        String URL ="https://cuddlebug-api.herokuapp.com/friend";
        new AsyncPost().execute(URL);
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

    public class AsyncPost extends AsyncTask<String, Void, String>{
        protected String doInBackground(String... params) {
            HttpURLConnection connection;
            try {
                //Open a new URL connection
                connection = (HttpURLConnection) new URL(params[0])
                        .openConnection();

                //Defines a HTTP request type
                connection.setRequestMethod("POST");

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String userId = user.getUid();
                String friendId=add_friend.getUid();
                String friendName=add_friend.getName();


                //Sets headers: Content-Type, Authorization
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");

                //Add POST data in JSON format
                JSONObject jsonParam = new JSONObject();
                try {

                    jsonParam.put("friendId", friendId);
                    jsonParam.put("friendName", friendName);
                    jsonParam.put("userId", userId);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("test", jsonParam.toString());

                //Create a writer object and make the request
                OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream());
                outputStream.write(jsonParam.toString());
                outputStream.flush();
                outputStream.close();

                //Get the Response code for the request
                Log.d("UID",userId);
                Log.d("Response", connection.getResponseMessage() + "");
                return "";
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }
        protected void onPostExecute(String s) {


        }
    }

}


