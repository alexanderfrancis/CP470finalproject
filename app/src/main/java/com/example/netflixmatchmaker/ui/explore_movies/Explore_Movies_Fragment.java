package com.example.netflixmatchmaker.ui.explore_movies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;

import com.example.netflixmatchmaker.CardStackAdapter;
import com.example.netflixmatchmaker.ItemModel;
import com.example.netflixmatchmaker.R;
import com.example.netflixmatchmaker.CardStackCallback;
import com.example.netflixmatchmaker.ItemModel;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Explore_Movies_Fragment extends Fragment {

    private static final String TAG = Explore_Movies_Fragment.class.getSimpleName();
    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;
    private ArrayList<ItemModel> movies = new ArrayList();
    private CardStackView cardStackView;
    int top_position;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_explore_movies, container, false);
        callAPI();
        init(root);
        manager.setCanScrollVertical(false);
        return root;
    }

    private void init(View root) {
        cardStackView = root.findViewById(R.id.card_stack_view);
        manager = new CardStackLayoutManager(getContext(), new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {
                Log.d(TAG, "onCardDragging: d=" + direction.name() + " ratio=" + ratio);
            }

            @Override
            public void onCardSwiped(Direction direction) {
                Log.d(TAG, "onCardSwiped: p=" + manager.getTopPosition() + " d=" + direction);
                if (direction == Direction.Right){
                    Toast.makeText(getContext(), "Liked", Toast.LENGTH_SHORT).show();
                    top_position=manager.getTopPosition();

                    String URL = "http://cuddlebug-api.herokuapp.com/liked/5fbf09028d2518d968fab249";
                    new AsyncPost().execute(URL);
                }
                if (direction == Direction.Top){
                    Toast.makeText(getContext(), "Direction Top", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Left){
                    Toast.makeText(getContext(), "Disliked", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Bottom){
                    Toast.makeText(getContext(), "Direction Bottom", Toast.LENGTH_SHORT).show();
                }

                // Paginating
                if (manager.getTopPosition() == adapter.getItemCount() - 5){
                    paginate();
                }

            }

            @Override
            public void onCardRewound() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardCanceled() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardAppeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_name);
                Log.d(TAG, "onCardAppeared: " + position + ", name: " + tv.getText());
            }

            @Override
            public void onCardDisappeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_name);
                Log.d(TAG, "onCardAppeared: " + position + ", name: " + tv.getText());
            }
        });
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.FREEDOM);
        manager.setCanScrollHorizontal(true);
        manager.setSwipeableMethod(SwipeableMethod.Manual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        adapter = new CardStackAdapter(movies);
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());
    }

    private void paginate() {
        List<ItemModel> old = adapter.getItems();
        List<ItemModel> next = new ArrayList<>(movies);
        CardStackCallback callback = new CardStackCallback(old, next);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        adapter.setItems(next);
        result.dispatchUpdatesTo(adapter);
    }

    private void callAPI() {
        String URL = "https://cuddlebug-api.herokuapp.com/movie";
        new AsycnGet().execute(URL);
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
                        ItemModel item = new ItemModel(obj.getString("Poster"), obj.getString("Title"), obj.getString("Year"), "8.8",obj.getString("imdbID") );

                        movies.add(item);
                    }
                    adapter = new CardStackAdapter(movies);
                    cardStackView.setAdapter(adapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public class AsyncPost extends AsyncTask<String, Void, String>{
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                JSONObject dataToSend= new JSONObject();

                ItemModel movie=movies.get(top_position);

                dataToSend.put("imdbID","tt0839995");
                dataToSend.put("userID","5fbf09028d2518d968fab249");

                connection.setRequestMethod("POST");
//                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//                connection.setRequestProperty("Accept", "*/*");

                connection.setDoOutput(true);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                writer.write(dataToSend.toString());
                writer.close();

                connection.connect();

                // Response: 400
                Log.e("Response", connection.getResponseMessage() + "");

            } catch (Exception e) {
                Log.e(e.toString(), "Something with request");
            }

            return null;
        }
        protected void onPostExecute(String s) {


        }
    }

}
