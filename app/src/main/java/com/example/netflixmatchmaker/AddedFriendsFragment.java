package com.example.netflixmatchmaker;

import android.content.Context;
import android.content.Intent;
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
        friends_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fragmentManager= getActivity().getSupportFragmentManager();
                MoviesFriendFragment fragment= new MoviesFriendFragment();

                FriendModel friend= friendsAdapter.getItem(position);
                Log.i("In ADDEDFRIENDS", "Friend:" +friend.getName());


                Bundle sendData= new Bundle();
                sendData.putString("friendName",friend.getName());
                sendData.putString("friendID",friend.getUid());
                fragment.setArguments(sendData);


                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment)
                        .commit();

//                Intent intent = new Intent(getActivity().getBaseContext(),
//                        ExploreActivity.class);
//                intent.putExtra("message", "hello");
//                getActivity().startActivity(intent);
//                LocationList.remove(item);
//                adapter = new MyAdapter(getActivity(),LocationList);
//                list.setAdapter(adapter);
            }

        });

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

//                    for (int i = 0; i < a.length(); i++) {
//                        JSONObject obj =  a.getJSONObject(i);
//                        FriendModel item = new FriendModel(obj.getString("friendName"),null,obj.getString("friendId"));
//
//
//                        Friends.add(item);
//                    }

                    // Iterates through all of the movies and doesn't display already liked movies
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject obj = a.getJSONObject(i);
                        FriendModel item = new FriendModel(obj.getString("friendName"),null,obj.getString("friendId"));

                        int j = 0;
                        boolean isFound = false;
                        while (j < Friends.size() && !isFound) {
                            String friend_uid = Friends.get(j).getUid();
                            if (friend_uid.equals(item.getUid())) {
                                isFound = true;
                            }
                            j++;
                        }
                        // Only adds moves to list if it hasn't been liked prior
                        if(!isFound)
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