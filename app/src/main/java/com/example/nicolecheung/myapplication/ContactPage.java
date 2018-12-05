package com.example.nicolecheung.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Button;

public class ContactPage extends AppCompatActivity {

    private static final String TAG = "ContactPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_page);
    }
    public void start(View v) {
        Intent i = new Intent (this, MainActivity.class);
        startActivity(i);
    }
}
