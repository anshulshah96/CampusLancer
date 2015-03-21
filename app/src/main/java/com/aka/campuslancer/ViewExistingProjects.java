package com.aka.campuslancer;

import android.app.Activity;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.Timer;


public class ViewExistingProjects extends Activity {
    private ParseQueryAdapter<HirePost> existingProjectsQueryAdapter;
    private static final int MAX_POST_SEARCH_RESULTS = 30;
    ParseQuery<HirePost> q = HirePost.getQuery();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_existing_projects);




        final ProgressDialog dialog = new ProgressDialog(ViewExistingProjects.this);
        dialog.setMessage("Loading data...");
        dialog.show();

        ParseObject.registerSubclass(HirePost.class);
        ParseQueryAdapter.QueryFactory<HirePost> factory =
                new ParseQueryAdapter.QueryFactory<HirePost>() {
                    public ParseQuery<HirePost> create() {
                        ParseQuery<HirePost> query = HirePost.getQuery();
                        query.include("user");
                        query.orderByDescending("createdAt");
                        query.whereContains("username", ParseUser.getCurrentUser().getUsername());
                        query.setLimit(MAX_POST_SEARCH_RESULTS);
                        q=query;
                        return query;
                }
        };




        if(q==null){

            Toast.makeText(getApplicationContext(),"You don't have any projects",Toast.LENGTH_SHORT).show();

        }
        existingProjectsQueryAdapter = new ParseQueryAdapter<HirePost>(this, factory) {
            @Override
            public View getItemView(HirePost post, View view, ViewGroup parent) {

                if (view == null) {
                    view = View.inflate(getContext(), R.layout.existing_projects_item, null);
                }
                TextView topicView = (TextView) view.findViewById(R.id.existing_projects_topic);
                TextView bidView = (TextView) view.findViewById(R.id.existing_projects_bid);
                TextView Description = (TextView) view.findViewById(R.id.existing_projects_description);

                String topictxt=post.getTopic();
                String bidtxt=""+post.getBid();
                String description = post.getDescription();

                topicView.setText(topictxt);
                bidView.setText(bidtxt);
                Description.setText(description);
                if(q!=null){
                    dialog.dismiss();
                }


                return view;
            }
        };

        // Disable automatic loading when the adapter is attached to a view.
        existingProjectsQueryAdapter.setAutoload(false);
        // Disable pagination, we'll manage the query limit ourselves
        existingProjectsQueryAdapter.setPaginationEnabled(false);

        // Attach the query adapter to the view
        ListView postsListView = (ListView) findViewById(R.id.existingprojectsLV);
        postsListView.setAdapter(existingProjectsQueryAdapter);
        doListQuery();
    }

    private void doListQuery() {
        existingProjectsQueryAdapter.loadObjects();
    }
}
