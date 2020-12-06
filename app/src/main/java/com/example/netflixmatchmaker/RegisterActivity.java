package com.example.netflixmatchmaker;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.netflixmatchmaker.ui.explore_movies.Explore_Movies_Fragment;
import com.example.netflixmatchmaker.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEmail, mName, mPassword, mPasswordConfirmed;
    private Button registerButton;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        mEmail = (EditText) findViewById(R.id.emailRegister);
        mName = (EditText) findViewById(R.id.nameRegister);
        mPassword = (EditText) findViewById(R.id.password);
        mPasswordConfirmed = (EditText) findViewById(R.id.password2);
        registerButton = (Button) findViewById(R.id.createAcc);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });
    }
    private void callAPI() {
        String USER_URL ="https://cuddlebug-api.herokuapp.com/user";
        new AsyncPost().execute(USER_URL);
    }
    private void registerNewUser() {

        final String email, name, password, password2;
        email = mEmail.getText().toString();
        name = mName.getText().toString();
        password = mPassword.getText().toString();
        password2 = mPasswordConfirmed.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(name)){
            Toast.makeText(getApplicationContext(), "Please enter a name!", Toast.LENGTH_LONG).show();
            return;
        }
        if (!password.equals(password2)){
            Toast.makeText(getApplicationContext(), "Password does not match Confirmed Password!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();

                            //API create new user
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build();
                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d("ACTIVITY_TAG", "User profile updated.");
                                            }
                                        }
                                    });

                            callAPI();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
    public class AsyncPost extends AsyncTask<String, Void, String> {
        String name = mName.getText().toString();
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
                String userName = name;
                String userEmail = user.getEmail();

                //Sets headers: Content-Type, Authorization
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");

                //Add POST data in JSON format
                JSONObject jsonParam = new JSONObject();
                try {
                    jsonParam.put("name", userName);
                    jsonParam.put("email", userEmail);
                    jsonParam.put("uid", userId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("test", jsonParam.toString() + userName);

                //Create a writer object and make the request
                OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream());
                outputStream.write(jsonParam.toString());
                outputStream.flush();
                outputStream.close();

                //Get the Response code for the request
                Log.d("UID",userId);
                Log.d("userName", userName);
                Log.d("userEmail", userEmail);
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
