package com.aka.campuslancer;

import com.parse.Parse;
import com.parse.ParseACL;

import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.PushService;
import com.parse.SaveCallback;

import android.app.Application;
import android.util.Log;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here
        Parse.initialize(this, "gpSqLXFDsQg0oBtIg3ITgoYZLFiI9wkEF2tGiUR3", "pzEksVGPBG1iX8NkIoJ4V7hAPGoaTPo7dyNRkDs4");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
        PushService.setDefaultPushCallback(this, MainActivity.class);

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });
    }

}