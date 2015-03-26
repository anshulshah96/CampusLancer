package com.aka.campuslancer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import android.widget.AdapterView.OnItemClickListener;

import java.util.List;


public class WorkActivity extends Activity implements WorkDescriptionFragment.OnFragmentInteractionListener{
    private ParseQueryAdapter<HirePost> postsQueryAdapter;
    private static final int MAX_POST_SEARCH_RESULTS = 20;
    public static String username;
    public static String description;
    public static String topic;
    public static String mobileno;
    public static String projectId;

    ParseQuery<HirePost> q = HirePost.getQuery();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        final ProgressDialog dialog = new ProgressDialog(WorkActivity.this);
        dialog.setMessage("Loading data...");
        ParseObject.registerSubclass(HirePost.class);



        ParseQueryAdapter.QueryFactory<HirePost> factory =
                new ParseQueryAdapter.QueryFactory<HirePost>() {
                    public ParseQuery<HirePost> create() {
                        ParseQuery<HirePost> query = HirePost.getQuery();
                        query.include("user");
                        query.include("objectId");
                        query.whereContains("category",Welcome.category);
                        query.orderByDescending("createdAt");
                        query.setLimit(MAX_POST_SEARCH_RESULTS);
                        q=query;
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
                TextView descriptionView = (TextView) view.findViewById(R.id.post_description);
                TextView projectId = (TextView) view.findViewById(R.id.post_project_id);

                String topictxt=post.getTopic();
                String bidtxt=""+post.getBid();
                String enrolltxt=""+(post.getMobileNo()==0?post.getEnrol():post.getMobileNo());
                String unametxt=post.getUsername();
                String descriptiontxt = post.getDescription();
                String projectIdtxt = post.getObjectId();
                String nametxt = post.getname();
//                Log.d("pid: ",unametxt+"\t"+projectIdtxt);

                topicView.setText(topictxt);
                bidView.setText(bidtxt);
                enrolView.setText(enrolltxt);
                usernameView.setText(nametxt==""?unametxt:nametxt);
                descriptionView.setText(descriptiontxt);
                projectId.setText(projectIdtxt);

                if(q!=null){
                    dialog.dismiss();
                }
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

        postsListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                username = ((TextView) (view.findViewById(R.id.post_user))).getText().toString();
                description = ((TextView) (view.findViewById(R.id.post_description))).getText().toString();
                topic = ((TextView) (view.findViewById(R.id.post_topic))).getText().toString();
                mobileno = ((TextView) (view.findViewById(R.id.post_enrol))).getText().toString();
                projectId = ((TextView) (view.findViewById(R.id.post_project_id))).getText().toString();
//                Log.i("pid: ",projectId);

                Intent intent = new Intent(WorkActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });

        postsQueryAdapter.addOnQueryLoadListener(new ParseQueryAdapter.OnQueryLoadListener<HirePost>() {
            @Override
            public void onLoading() {
                dialog.show();
            }

            @Override
            public void onLoaded(List<HirePost> hirePosts, Exception e) {
                if(e==null)
                    dialog.dismiss();
                else{
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(),"No Data was Found",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        doListQuery();
    }

    /*
     * Set up a query to update the list view
     */
    private void doListQuery() {
        postsQueryAdapter.loadObjects();
    }

    public void onFragmentInteraction(Uri uri){

    }

}