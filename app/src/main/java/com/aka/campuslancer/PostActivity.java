package com.aka.campuslancer;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class PostActivity extends ActionBarActivity {

    Button postButton;
    EditText topic, description, bid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        postButton = (Button) findViewById(R.id.post);
        topic = (EditText) findViewById(R.id.topic);
        description = (EditText) findViewById(R.id.description);
        bid = (EditText) findViewById(R.id.bid);
        postButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                post();
            }
        });
    }

    private void post() {
        // 1
        HirePost post = new HirePost();
        String text = topic.getText().toString().trim();
        String text1 = description.getText().toString().trim();
        post.setUsername();
        post.setUser(ParseUser.getCurrentUser());
        post.setTopic(text);
        post.setDescription(text1);


        final ProgressDialog dialog = new ProgressDialog(PostActivity.this);
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