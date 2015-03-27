package com.aka.campuslancer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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
    private String[] categories;
    public String category="Android Development";
    public CustomProgressDialogBox dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hire);
        ParseObject.registerSubclass(HirePost.class);
        Parse.initialize(this, "gpSqLXFDsQg0oBtIg3ITgoYZLFiI9wkEF2tGiUR3", "pzEksVGPBG1iX8NkIoJ4V7hAPGoaTPo7dyNRkDs4");

        this.categories = new String[] {"Android Development","Web Development","Web Design","Content Writing","Bakar"};
        Spinner s =(Spinner) findViewById(R.id.spinners);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, categories);
        s.setAdapter(adapter);

        dialog = new CustomProgressDialogBox(Hire.this,"Posting...");

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category =  categories[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                category = "Android Development";
            }
        });


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
        post.setCategory(category);

        //To be Set
        post.setEnrol(0);
        post.setBid(Integer.parseInt(bid.getText().toString()));

        dialog.setMessage("Posting...");
        dialog.show();

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


