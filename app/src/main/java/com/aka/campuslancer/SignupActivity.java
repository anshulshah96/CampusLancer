package com.aka.campuslancer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class SignupActivity extends ActionBarActivity {

    Button signup;
    String usernametxt;
    String passwordtxt;
    String enrollmenttxt;
    String emailtxt;
    EditText password;
    EditText username;
    EditText enrollment;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username = (EditText) findViewById(R.id.usernamesignup);
        password = (EditText) findViewById(R.id.passwordsignup);
        enrollment = (EditText) findViewById(R.id.enrollmentsignup);
        email=(EditText)findViewById(R.id.emailsignup);
        signup = (Button) findViewById(R.id.signupbtn);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();
                enrollmenttxt = enrollment.getText().toString();
                emailtxt = email.getText().toString();
                ParseUser user = new ParseUser();
                user.setUsername(usernametxt);
                user.setPassword(passwordtxt);
                user.setEmail(emailtxt);
                user.put("enrollment",Integer.parseInt(enrollment.getText().toString()));
                // user.setEnrollment(enrollmenttxt);
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Show a simple Toast message upon successful registration
                            Toast.makeText(getApplicationContext(),
                                    "Successfully Signed up, please log in.",
                                    Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignupActivity.this,
                                    Welcome.class);
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Sign up Error", Toast.LENGTH_LONG)
                                    .show();
                            Log.e("Sign Up Error: ",e.getMessage());
                        }
                    }
                });
            }
        });


    }
}

