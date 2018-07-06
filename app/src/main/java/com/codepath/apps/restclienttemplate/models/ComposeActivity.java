package com.codepath.apps.restclienttemplate.models;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.apps.restclienttemplate.R;

public class ComposeActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        Button tweetButton = (Button) findViewById(R.id.bTweet);
        tweetButton.setOnClickListener(this);
    }

    // Get the tweet entered into edit text
    public String getTweetEntry(){
        EditText simpleEditText = (EditText) findViewById(R.id.etTweet);
        String tweetValue = simpleEditText.getText().toString();
        return tweetValue;
    }


    @Override
    public void onClick(View v) {

        Intent data = new Intent();
        data.putExtra("tweet", getTweetEntry());
        setResult(RESULT_OK, data);

        finish();
    }
}
