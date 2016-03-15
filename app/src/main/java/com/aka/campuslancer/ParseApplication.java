package com.aka.campuslancer;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.PushService;
import com.parse.SaveCallback;

/**
 * Created by root on 4/12/15.
 */
public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId(Keys.API_KEY)
                .clientKey(Keys.CLIENT_KEY)
                .server("http://172.25.12.120/parse/")   // '/' important after 'parse'
                .build());
        super.onCreate();
        Parse.enableLocalDatastore(this);
//        Parse.initialize(this,Keys.API_KEY,Keys.CLIENT_KEY);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseUser.enableAutomaticUser();
        ParseACL defaultacl=new ParseACL();
        defaultacl.setPublicReadAccess(true);
//        PushService.setDefaultPushCallback(this,MainActivity.class);
        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null)
                Log.d("com.parse.push","success");

                else
                    Log.d("com.parse.push","failed");
            }
        });
    }
}
