package com.aka.campuslancer;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class CustomProgressDialogBox extends ProgressDialog {

    private AnimationDrawable progressLogo;
    public TextView messagetv;

    public CustomProgressDialogBox(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_progress_dialog_box);

        ImageView logo = (ImageView) findViewById(R.id.progressLogo);
        logo.setBackgroundResource(R.drawable.custom_progress_dialog_animation);
        messagetv = (TextView) findViewById(R.id.message);
    }

    @Override
    public void show()
    {
        super.show();
        progressLogo.start();
    }

    @Override
    public void dismiss()
    {
        super.dismiss();
        progressLogo.stop();
    }

    public void setMessage(String text)
    {
        messagetv.setText(text);
    }

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
