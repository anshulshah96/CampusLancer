package com.aka.campuslancer;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;


public class CustomProgressDialogBox extends ProgressDialog {

    public TextView messagetv;
    public AnimationDrawable animation;
    private CharSequence dialogMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_progress_dialog_box);

        ImageView logo = (ImageView) findViewById(R.id.progressLogo);
        logo.setBackgroundResource(R.drawable.custom_progress_dialog_animation);
        animation = (AnimationDrawable) logo.getBackground();
        messagetv = (TextView) findViewById(R.id.message);
        messagetv.setText(dialogMessage);
    }


    public CustomProgressDialogBox(Context context, CharSequence dialogMessage) {
        super(context);
        this.dialogMessage = dialogMessage;

    }

    @Override
    public void show() {
        super.show();
        animation.start();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        animation.stop();
    }
/*
    public static ProgressDialog ctor(Context context) {
        CustomProgressDialogBox dialog = new CustomProgressDialogBox(context);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        return dialog;
    }*/
//    class VeryLongAsyncTask extends AsyncTask<Void, Void, Void> {
//        private final ProgressDialog progressDialog;
//
//        public VeryLongAsyncTask(Context ctx) {
//            progressDialog = CustomProgressDialogBox.ctor(ctx);
//        }
//        @Override
//        protected Void doInBackground(Void... params) {
//            // sleep for 5 seconds
//            try { Thread.sleep(5000); }
//            catch (InterruptedException e) { e.printStackTrace(); }
//
//            return null;
//        }
//        @Override
//        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);
//            textView.setVisibility(View.VISIBLE);
//
//            progressDialog.hide();
//        }
//    }


/*    public void setCustomMessage(CharSequence message) {
                messagetv.setText(message);

    }

    public static CustomProgressDialogBox show(Context context,CharSequence string){
        CustomProgressDialogBox dialog = new CustomProgressDialogBox(context);
        dialog.setCustomMessage(string);
        dialog.setCancelable(false);
        dialog.show();
        return null;
    }*/
//    public static ProgressDialog cnstr(Context context) {
//        CustomProgressDialogBox dialog = new CustomProgressDialogBox(context);
//        dialog.setIndeterminate(true);
//        dialog.setCancelable(false);
//        return dialog;
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_custom_progress_dialog_box, menu);
//        return true;
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

}