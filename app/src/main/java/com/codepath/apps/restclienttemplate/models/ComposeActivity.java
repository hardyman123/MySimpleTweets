package com.codepath.apps.restclienttemplate.models;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity implements View.OnClickListener {

    private TwitterClient client;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        Button tweetButton = (Button) findViewById(R.id.bTweet);
        tweetButton.setOnClickListener(this);

        client = TwitterApp.getRestClient(this);

    }

    // Get the tweet words entered into edit text
    public String getTweetEntry(){
        EditText simpleEditText = (EditText) findViewById(R.id.etTweet);
        String tweetValue = simpleEditText.getText().toString();
        return tweetValue;
    }



    @Override
    public void onClick(View v) {

        client.sendTweet(getTweetEntry(), new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Intent data = new Intent();
                // unnecessary but puts the actual content of the tweet into our data sent
                data.putExtra("tweetText", getTweetEntry());

                try {
                    Tweet tweet = Tweet.fromJSON(response);
                    data.putExtra("tweet", Parcels.wrap(tweet));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                setResult(RESULT_OK, data);

                System.out.println("Tweet was sent");
                finish();
                //super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }
        });


    }
}
