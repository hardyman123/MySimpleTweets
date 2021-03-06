package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Tweet {

    public Tweet(){

    }



    // list out the attributes
    public String body;
    public long uid; // database ID for the tweet
    public User user;
    public String createdAt;
    public String formattedAbsoluteTime;

    // deserialize the JSON
    public static Tweet fromJSON(JSONObject jsonObject) throws JSONException{
        Tweet tweet = new Tweet();

        // extract the values from JSON
        tweet.body = jsonObject.getString("text");
        tweet.uid = jsonObject.getLong("id");
        tweet.createdAt = jsonObject.getString("created_at");


        //tweet.formattedAbsoluteTime = TimeFormatter.getTimeStamp(tweet.createdAt);
        tweet.formattedAbsoluteTime = TimeFormatter.getTimeDifference(tweet.createdAt);


        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));


        return  tweet;
    }

}
