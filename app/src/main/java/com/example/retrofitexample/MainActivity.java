package com.example.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private APIService mAPIService;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAPIService = ApiUtils.getAPIService();
        gson = new Gson();
        String title = "Selam";
        String body = "Furkan Kahraman";
        sendPost(title, body);

    }

    public void sendPost(String title, String body) {
        mAPIService.savePost(title, body,2).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(response.isSuccessful()) {
                    Log.i("RefrofitExample", "post submitted to API." + response.body().toString());
                    Post post = gson.fromJson("{\"title\":\"title\", \"body\":\"Furkan Kahraman\", \"userId\":2, \"id\":101}", Post.class);
                    Log.i("RefrofitExample", "post toString : " + post.toString());
                    Log.i("RefrofitExample", "post getBody : " + post.getBody());
                    Log.i("RefrofitExample", "post getTitle : " + post.getTitle());
                    Log.i("RefrofitExample", "post getId : " + post.getId());
                    Log.i("RefrofitExample", "post getUserId : " + post.getUserId());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("RefrofitExample", "Unable to submit post to API.");
            }
        });
    }
}
