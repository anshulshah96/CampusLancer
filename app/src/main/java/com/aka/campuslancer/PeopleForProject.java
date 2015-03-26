package com.aka.campuslancer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.List;


public class PeopleForProject extends Activity {

    ParseQuery<BidPost> q = BidPost.getQuery();
    ParseQueryAdapter<BidPost> postsQueryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_for_project);
        Intent intent = getIntent();
        final String pid = intent.getStringExtra("project_id");

        final ProgressDialog dialog = new ProgressDialog(PeopleForProject.this);
        dialog.setMessage("Loading data...");
        dialog.show();

        ParseObject.registerSubclass(BidPost.class);


        ParseQueryAdapter.QueryFactory<BidPost> factory =
                new ParseQueryAdapter.QueryFactory<BidPost>() {
                    public ParseQuery<BidPost> create() {
                        ParseQuery<BidPost> query = BidPost.getQuery();
                        query.include("bidder_user");
//                        query.include("project_id");
//                        ParseObject pobj = ParseObject.createWithoutData("HireData",pid);
//                        query.whereEqualTo("pointer_id",pobj);
                        query.whereContains("projectId",pid);
                        Log.d("project id", pid);
                        query.orderByDescending("createdAt");
//                        query.setLimit(20);
                        q = query;
                        return query;
                    }
                };
        postsQueryAdapter = new ParseQueryAdapter<BidPost>(this, factory) {
            @Override
            public View getItemView(BidPost post, View view, ViewGroup parent) {

                if (view == null) {
                    view = View.inflate(getContext(), R.layout.peopleforprojectitem, null);
                }
                TextView projectuser = (TextView) view.findViewById(R.id.peopleproject_user);
                TextView projectenrol = (TextView) view.findViewById(R.id.peopleproject_enrol);
                TextView projectbid = (TextView) view.findViewById(R.id.peopleproject_bid);

                String pb = "" + post.getBid();
                String pu = "" + post.getBidderUsername();


                if(q!=null){
                    dialog.dismiss();
                }

                projectbid.setText(pb);
                projectuser.setText(pu);
                projectenrol.setText(""+post.getMobileNo());

                return view;

            }
        };


        // Disable automatic loading when the adapter is attached to a view.
        postsQueryAdapter.setAutoload(false);

        // Disable pagination, we'll manage the query limit ourselves
        postsQueryAdapter.setPaginationEnabled(false);

        // Attach the query adapter to the view
        ListView postsListView = (ListView) findViewById(R.id.peopleproject_listview);


        postsListView.setAdapter(postsQueryAdapter);

        doListQuery();
    }
    private void doListQuery() {
        postsQueryAdapter.loadObjects();
    }
}


