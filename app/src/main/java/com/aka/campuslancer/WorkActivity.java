package com.aka.campuslancer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;


public class WorkActivity extends Activity {
    private ParseQueryAdapter<HirePost> postsQueryAdapter;
    private static final int MAX_POST_SEARCH_RESULTS = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        ParseObject.registerSubclass(HirePost.class);
        ParseQueryAdapter.QueryFactory<HirePost> factory =
                new ParseQueryAdapter.QueryFactory<HirePost>() {
                    public ParseQuery<HirePost> create() {


//                        final ProgressDialog dialog = new ProgressDialog(WorkActivity.this);
//                        dialog.setMessage("Retrieving Data...");
//                        dialog.show();
                        ParseQuery<HirePost> query = HirePost.getQuery();
                        query.include("user");
//                        query.include("bid");
//                        query.include("enrollment");
//                        query.include("topic");
                        query.orderByDescending("createdAt");
                        query.setLimit(MAX_POST_SEARCH_RESULTS);
                        return query;
                    }
                };
        
        
        postsQueryAdapter = new ParseQueryAdapter<HirePost>(this, factory) {
            @Override
            public View getItemView(HirePost post, View view, ViewGroup parent) {
                if (view == null) {
                    view = View.inflate(getContext(), R.layout.post_item, null);
                }
                TextView usernameView = (TextView) view.findViewById(R.id.post_user);
                TextView topicView = (TextView) view.findViewById(R.id.post_topic);
                TextView bidView = (TextView) view.findViewById(R.id.post_bid);
                TextView enrolView = (TextView) view.findViewById(R.id.post_enrol);
                topicView.setText(post.getTopic());
                bidView.setText(post.getBid());
                enrolView.setText(post.getEnrol());
                usernameView.setText(post.getUser().getUsername());
                return view;
            }
        };

        // Disable automatic loading when the adapter is attached to a view.
        postsQueryAdapter.setAutoload(false);
        // Disable pagination, we'll manage the query limit ourselves
        postsQueryAdapter.setPaginationEnabled(false);

        // Attach the query adapter to the view
        ListView postsListView = (ListView) findViewById(R.id.work_listview);
        postsListView.setAdapter(postsQueryAdapter);

        doListQuery();

    }


    /*
     * Set up a query to update the list view
     */
    private void doListQuery() {
            postsQueryAdapter.loadObjects();
        Log.i("query:",String.valueOf(postsQueryAdapter.isEmpty()));

    }
//
//
//    public void onResume(){
//        super.onResume();
//        doListQuery();
//
//    }


}