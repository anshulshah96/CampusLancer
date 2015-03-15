package com.aka.campuslancer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/**
 * Created by anshul on 14/3/15.
 */
public class Hire extends Activity {
    Button postButton;
    EditText topic, description, bid;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hire);
        ParseObject.registerSubclass(HirePost.class);
         Parse.initialize(this, "gpSqLXFDsQg0oBtIg3ITgoYZLFiI9wkEF2tGiUR3", "pzEksVGPBG1iX8NkIoJ4V7hAPGoaTPo7dyNRkDs4");
            postButton = (Button) findViewById(R.id.HirePost);
            topic = (EditText) findViewById(R.id.TopicField);
            description = (EditText) findViewById(R.id.DescriptionField);
            bid = (EditText) findViewById(R.id.InitialBidField);
            postButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    post();
                }
            });
        }

    private void post() {
        // 1
        HirePost post = new HirePost();
        String text =  topic.getText().toString().trim();
        String text1 = description.getText().toString().trim();
        post.setUsername();
        post.setUser(ParseUser.getCurrentUser());
        post.setTopic(text);
        post.setDescription(text1);


        final ProgressDialog dialog = new ProgressDialog(Hire.this);
        dialog.setMessage("Posting...");
        dialog.show();


        // 2
        ParseACL acl = new ParseACL();
        acl.setPublicReadAccess(true);
        post.setACL(acl);

        // 3
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                dialog.dismiss();
                finish();
            }
        });

    }
}


